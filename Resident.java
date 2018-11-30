package POS18S2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *This class will be responsible to save resident’s data read from input records fle and provide methods to process resident’s
 * records according to instructons in instructons input fle
 * This class’ instance and statc methods will be used by Instructons class to process queries
 * */

class Resident {
    private String name;
    private String address;
    private String petType;
    private String phone;
    private String postCode;
    private String birthDay;
//    private static ArrayList<Resident> residentsList;
    private static ArrayList<Resident> residentsList = new ArrayList<>();;

    Resident() {
        this.name = null;
        this.birthDay = null;
        this.phone = null;
        this.postCode = null;
        this.address = null;
        this.petType = null;

    }

    Resident(String name, String phone, String postCode, String bday, String address, String petType) {
        if(!name.isEmpty()) this.name = name; else this.name = null;
        if(!bday.isEmpty()) this.birthDay = bday;  else this.birthDay = null;
        if(!phone.isEmpty()) this.phone = phone;  else this.phone = null;
        if(!postCode.isEmpty()) this.postCode = postCode;  else this.postCode = null;
        if(!address.isEmpty()) this.address = address;  else this.address = null;
        if(!petType.isEmpty()) this.petType = petType;  else this.petType = null;

    }

    Resident(String name, String phone, String postCode) {
        this.name = name;
        this.birthDay = null;
        this.phone = phone;
        this.postCode = postCode;
        this.address = null;
        this.petType = null;

    }

    public void setName(String rName){
        this.name = rName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthDay(String birthDay){
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPetType() {
        return petType;
    }

    public String getBirthDay(){
        return this.birthDay;
    }

    public void addToResidentsList(){
        residentsList.add(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("name ").append(name).append("\r\n");
        if(birthDay != null)
            sb.append("birthday ").append(birthDay).append("\r\n");
        if(address != null)
            sb.append("address ").append(address).append("\r\n");
        sb.append("postcode ").append(postCode).append("\r\n");
        sb.append("phone ").append(phone).append("\r\n");
        if(petType != null)
            sb.append("pet ").append(petType).append("\r\n");

        return sb.toString();

    }

    public static ArrayList<Resident> getResidentsList(){
        return residentsList;
    }

    public static void addOrUpdateResident(String name, String phone, String postCode, String bday, String address, String petType){
        Resident r = null;
        if(PosUtilities.isNameValid(name) && PosUtilities.isPhoneValid(phone)) {
            r = findResident(name, phone);
        }
        if(r != null){
            StringBuilder sb = new StringBuilder();
            if(PosUtilities.isResidentRecordValid(r)){
                if(postCode != null && PosUtilities.isPostCodeValid(postCode.trim()))
                    r.postCode = postCode;
                if(address != null && PosUtilities.isAddressValid(address.trim()))
                    r.address = address;
                if(bday != null && PosUtilities.isBDayValid(bday, sb)) {
                    bday = sb.toString();
                    r.birthDay = sb.toString();
                }
                if(petType != null && PosUtilities.isPetTypeValid(petType.trim()))
                    r.petType = petType;
            }

        } else {
            Resident r1 = new Resident();
            r1.name = name;
            r1.phone = phone;
            r1.postCode = postCode;
            r1.birthDay = bday;
            r1.address = address;
            r1.petType = petType;
            if(PosUtilities.isResidentRecordValid(r1)){
                residentsList.add(r1);
            }
        }
    }


    public static void sortResidentsList(){
        int i = 0;
        for (; i < residentsList.size(); i++) {
            for(int j = 1; j < residentsList.size()-i; j++){
                if(residentsList.get(j-1).name.compareTo(residentsList.get(j).name) > 0){

                    Resident tmp = residentsList.get(j-1);
                    residentsList.set(j-1, residentsList.get(j));
                    residentsList.set(j, tmp);

                    //i++;
                } else if(residentsList.get(j-1).name.compareTo(residentsList.get(j).name) == 0){
                    if(residentsList.get(j-1).phone.compareTo(residentsList.get(j).phone) > 0){
                        Resident tmp = residentsList.get(j-1);
                        residentsList.set(j-1, residentsList.get(j));
                        residentsList.set(j, tmp);
                    }
                }
            }
        }

    }

    public static void deleteResident(String name, String phone){
        Resident resident = findResident(name, phone);
        if(resident != null) {
            for (Resident r : residentsList) {
                if (r.name.equals(resident.name) && r.phone.equals(resident.phone)) {
                    residentsList.remove(r);
                    break;
                }
            }
        }
    }

    private static Resident findResident(String name, String phone){
        for(Resident r:residentsList){
            if(r.name.equals(name) && r.phone.equals(phone))
                return r;
        }
        return null;
    }

    public static ArrayList<Resident> findResidentsByName(String name){

        ArrayList<Resident> residents = new ArrayList<>();

        if(PosUtilities.isNameValid(name) ) {

            for (Resident r : residentsList) {
                if (r.name.equals(name))
                    residents.add(r);
            }
        }

        return residents;
    }

    public static ArrayList<Resident> sortResidentsListByPhone(String name) {
        int i = 0;
        ArrayList<Resident> result = findResidentsByName(name);

        for (; i < result.size(); i++) {
            for (int j = 1; j < result.size() - i; j++) {
                if (result.get(j - 1).phone.compareTo(result.get(j).phone) > 0) {
                    Resident tmp = result.get(j - 1);
                    result.set(j - 1, result.get(j));
                    result.set(j, tmp);
                }
            }
        }
        return result;

    }

    private static ArrayList<Resident> findResidentsByPostCode(String postCode){

        ArrayList<Resident> residents = new ArrayList<>();

        if(PosUtilities.isPostCodeValid(postCode) ) {

            for (Resident r : residentsList) {
                if (r.postCode.equals(postCode))
                    residents.add(r);
            }
        }

        return residents;
    }

    private int getResidentAge(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Calendar bd = Calendar.getInstance();
        Date rbday = new Date();
        try{
            rbday = sdf.parse(this.getBirthDay());
        }catch (ParseException e){
            e.printStackTrace();

        }
        bd.setTime(rbday);

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return now.get(Calendar.YEAR) - bd.get(Calendar.YEAR);

    }


    public static Map<String, String> getAgeStatisticsByPostCode(String postCode){
        ArrayList<Resident> residents = findResidentsByPostCode(postCode);
        int totalUnderEighteen = 0;
        int totalOverSixtyFive = 0;
        int totalBwEighteenAndSixtyFive = 0;
        int totalUnknown = 0;
        int totalOwners = 0;

        Map<String, String> ageStatistics = new HashMap<>();

        if(!residents.isEmpty()){
            for(Resident r : residents){
                if(r.getBirthDay() != null){
                    totalOwners++;
                    if(r.getResidentAge() < 18)
                        totalUnderEighteen++;
                    else if(r.getResidentAge() > 65)
                        totalOverSixtyFive++;
                    else if(r.getResidentAge() > 18 && r.getResidentAge() < 65)
                        totalBwEighteenAndSixtyFive++;
                    else
                        totalUnknown++;
                }


            }
        }

        double underEighteenPercentage = 0;
        double overSixtyFivePercentage = 0;
        double bwEighteenAndSixtyFivePercentage = 0;
        double unknownPercentage = 0;



        if(totalOwners != 0){
            underEighteenPercentage = ((double)totalUnderEighteen / totalOwners) * 100;
            overSixtyFivePercentage = ((double)totalOverSixtyFive / totalOwners) * 100;
            bwEighteenAndSixtyFivePercentage = ((double)totalBwEighteenAndSixtyFive / totalOwners) * 100;
            unknownPercentage = ((double)totalUnknown / totalOwners) * 100;
        }


        String underEighteenPercentageString = String.format("%.2f%%", underEighteenPercentage);
        String overSixtyFivePercentageString = String.format("%.2f%%", overSixtyFivePercentage);
        String bwEighteenAndSixtyFivePercentageString = String.format("%.2f%%", bwEighteenAndSixtyFivePercentage);
        String unknownPercentageString = String.format("%.2f%%", unknownPercentage);


        ageStatistics.put("UnderEighteen", underEighteenPercentageString);
        ageStatistics.put("BetweenEighteenAndSixtyFive", bwEighteenAndSixtyFivePercentageString);
        ageStatistics.put("OverSixtyFive", overSixtyFivePercentageString);
        ageStatistics.put("Unknown", unknownPercentageString);
        ageStatistics.put("TotalOwners", Integer.toString(totalOwners));

        return ageStatistics;

    }

    public static Map<String, Map<String, Integer>> getAllPetStatisticsByPostCode(){
        Map<String, Map<String, Integer>> result = new HashMap<>();
        for(Resident r : residentsList){
            if (r.getPetType() == null) {
                continue;
            }
            String[] pets = r.getPetType().split("\\s");
            for(String pet : pets) {
                if(result.containsKey(pet)){
                    Integer tmp = result.get(pet).putIfAbsent(r.getPostCode(), 1);
                    if (tmp != null) {
                        result.get(pet).put(r.getPostCode(), tmp + 1);
                    }
                }else{
                    result.put(pet, new LinkedHashMap<>());
                    result.get(pet).put(r.getPostCode(), 1);
                }
            }
        }

        return result;

    }

}
