package POS18S2;

import javafx.geometry.Pos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static POS18S2.PosUtilities.getParametersKeyValues;

class ProcessInputFiles {
    private File f;
    private String filePath;
    private Scanner scn;
    private Resident resident;

    ProcessInputFiles() {
        this.filePath = null;
        resident = new Resident();
        f = null;
        scn = null;
    }

    public void readMembersFile(String filePath){
        try {
            this.filePath = filePath;
            f = new File(filePath);
            scn = new Scanner(new FileInputStream(f));
            String key, value;
            String[] kv;
            while (scn.hasNextLine()) {

                String line = scn.nextLine();
                Scanner sc = new Scanner(line);
                if (!line.isEmpty()) {
                    kv = line.split("\\s", 2);

                    key = kv[0];
                    value = kv[1];

                    if (key.equalsIgnoreCase("name")) {
                        if (PosUtilities.isNameValid(value))
                            resident.setName(value);
                    } else if (key.equalsIgnoreCase("address")) {
                        if (PosUtilities.isAddressValid(value))
                            resident.setAddress(value);
                    } else if (key.equalsIgnoreCase("postcode")) {
                        if (PosUtilities.isPostCodeValid(value))
                            resident.setPostCode(value);
                    } else if (key.equalsIgnoreCase("phone")) {
                        if (PosUtilities.isPhoneValid(value))
                            resident.setPhone(value);
                    } else if (key.equalsIgnoreCase("birthday")) {
                        StringBuilder sb = new StringBuilder();
                        if (PosUtilities.isBDayValid(value, sb))
                            resident.setBirthDay(sb.toString());
                    } else if (key.equalsIgnoreCase("pet")) {
                        if (PosUtilities.isPetTypeValid(value))
                            resident.setPetType(value);
                    } else {
                        continue;
                    }


                } else {

                    if (PosUtilities.isResidentRecordValid(resident)) {
                        resident.addToResidentsList();
                    }

                    resident = new Resident();
                }

            }

            if (PosUtilities.isResidentRecordValid(resident)) {
                resident.addToResidentsList();
            }

            scn.close();

        } catch (FileNotFoundException fex) {
            System.out.println(fex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void readAndExecuteInstructionsFile(String filePath){

        try {
            this.filePath = filePath;
            f = new File(filePath);
            scn = new Scanner(new FileInputStream(f));
            String key = null;
            String parameterString = null;
            String name = null, phone = null, birthDay = null, address = null, postCode = null, petType = null;
            Map<String, String> paraemtersMap = null;
            while (scn.hasNextLine()) {

                String line = scn.nextLine();

                if (!line.isEmpty()) {
                    key = PosUtilities.getInstructionType(line);

                    if (!PosUtilities.isInstructionTypeValid(key))
                        continue;

                    if (key.equalsIgnoreCase("update")) {

                        paraemtersMap = PosUtilities.getParametersKeyValues(line);

                        for (String pkey : paraemtersMap.keySet()) {
                            if (pkey.equalsIgnoreCase("name")) {
                                name = paraemtersMap.get(pkey);
                            } else if (pkey.equalsIgnoreCase("phone")) {
                                phone = paraemtersMap.get(pkey);
                            } else if (pkey.equalsIgnoreCase("address")) {
                                address = paraemtersMap.get(pkey);
                            } else if (pkey.equalsIgnoreCase("pet")) {
                                petType = paraemtersMap.get(pkey);
                            } else if (pkey.equalsIgnoreCase("birthday")) {
                                birthDay = paraemtersMap.get(pkey);
                                StringBuilder sb = new StringBuilder();
                                if (PosUtilities.isBDayValid(birthDay.trim(), sb))
                                    birthDay = sb.toString();
                            } else if (pkey.equalsIgnoreCase("postcode")) {
                                postCode = paraemtersMap.get(pkey);
                            }

                        }

                        Resident.addOrUpdateResident(name, phone, postCode, birthDay, address, petType);

                    } else if (key.equalsIgnoreCase("delete")) {

                        parameterString = PosUtilities.getInstructionParameters(line);
                        String[] nameAndPhone = parameterString.split(";", 2);

                        for (String s : nameAndPhone) {

                            if (PosUtilities.isNameValid(s.trim())) {
                                name = s;
                            }
                            if (PosUtilities.isPhoneValid(s.trim())) {
                                phone = s;
                            }
                        }

                        if (phone != null && name != null)
                            Resident.deleteResident(name.trim(), phone.trim());

                    } else if (key.equalsIgnoreCase("query")) {

                        parameterString = PosUtilities.getInstructionParameters(line);
                        String[] queryString = parameterString.split("\\s", 2);

                        if (queryString.length == 2 && queryString[0].equalsIgnoreCase("name")) {

                            ArrayList<Resident> matchedResidents = Resident.sortResidentsListByPhone(queryString[1]);

                            for(Resident r : matchedResidents){
                                Query query = new Query();
                                query.setQueryType("name");
                                query.setResident(r);
                                Query.queries.add(query);
                            }

                        } else if (queryString.length == 2 && queryString[1].equalsIgnoreCase("age")) {

                            if(PosUtilities.isPostCodeValid(queryString[0])){
                                Query query = new Query();
                                query.setQueryType("age");
                                query.setPostCode(queryString[0]);
                                Query.queries.add(query);
                            }



                        } else if (queryString.length == 1 && queryString[0].equalsIgnoreCase("pet")) {

                            Query query = new Query();
                            query.setQueryType("pet");
                            Query.queries.add(query);

                        }


                    } else if (key.equalsIgnoreCase("sort")) {

                        Resident.sortResidentsList();

                    } else {
                        continue;
                    }

                    line = null;
                    name = null;
                    phone = null;
                    birthDay = null;
                    address = null;
                    postCode = null;
                    petType = null;

                } else {

                    continue;
                }
            }

            scn.close();

        } catch (FileNotFoundException fex) {
            System.out.println(fex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
