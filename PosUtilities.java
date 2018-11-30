package POS18S2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This class will provide statc methods to validate input resident’s felds, records, instructon type, instructon parameters,
 * input fles, output fles
 * This class will use objects and methods from Resident and Instructon classes
 * */

//default modifier is package private modifier
class PosUtilities {

    //Private argument less constructor to restrain user for creating new instances of this class

    private PosUtilities(){}

    static boolean filePathValidation(String filePath){

        return Pattern.matches("\\w+\\.\\w+", filePath);

    }

    static boolean isNameValid(String name){

        if(name == null)
            return false;

        return !name.isEmpty() && Pattern.matches("[a-zA-Z\\s]+", name);

    }

    static boolean isBDayValid(String bday, StringBuilder sb){

        if(bday == null)
            return true;

        if(bday.isEmpty())
            return true;

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        sf.setLenient(false);
        String dateStr = bday;
        String[] tmp;
        String regStr = "\\d+\\D\\d+\\D\\d+";
        if (dateStr.matches(regStr)) {
            tmp = dateStr.split("\\D");
            if (tmp.length == 3) {
                for (int i = 0; i < 2; ++i) {
                    if (tmp[i].length() < 2)
                        tmp[i] = "0" + tmp[i];
                }
                dateStr = tmp[0] + "/" + tmp[1] + "/" + tmp[2];
            }
        }
        try {
            Date dt = sf.parse(dateStr);
            sb.append(dateStr);
            return true;

        } catch (ParseException e) {
            return false;
        }

    }

    static boolean isPostCodeValid(String pCode){
        if(pCode == null)
            return false;

        return !pCode.isEmpty() && Pattern.matches("\\d{4}+", pCode);

    }

    static boolean isPhoneValid(String phone){

        if(phone == null)
            return false;

        String p = phone.trim();

        return !phone.isEmpty() && Pattern.matches("\\d{8}+", p);

    }

    static boolean isPetTypeValid(String petType){

        if(petType == null)
            return true;

        return petType.isEmpty() || Pattern.matches("[a-zA-Z\\s]+", petType);

    }

    static boolean isAddressValid(String address){

        if(address == null)
            return true;

        String[] ss = address.split(",");

        return address.isEmpty() || (!ss[1].isEmpty());

    }

    static boolean isResidentRecordValid(Resident resident){
        String name = resident.getName();
        String phone = resident.getPhone();
        String pCode = resident.getPostCode();
        String petType = resident.getPetType();
        String bday = resident.getBirthDay();
        String address = resident.getAddress();

        if(!isPetTypeValid(petType))
            resident.setPetType(null);
        if(!isBDayValid(bday, new StringBuilder()))
            resident.setBirthDay(null);
        if(!isAddressValid(address))
            resident.setAddress(null);

        return ((isNameValid(name) && isPhoneValid(phone) && isPostCodeValid(pCode)));


    }

    public static boolean isInstructionTypeValid(String instruction){

        return (instruction.equalsIgnoreCase("update") || instruction.equalsIgnoreCase("query") ||
                instruction.equalsIgnoreCase("delete") || instruction.equalsIgnoreCase("sort"));


    }

    public static Map<String, String> getParametersKeyValues(String instParameters){

        String[] insString = instParameters.split("\\s", 2);

        String parameters = insString[1];

        String[] paraSplits = parameters.split(";");

        Map<String, String> kv = new HashMap<>();

        for (String paraSplit : paraSplits) {
            String[] vals = paraSplit.trim().split("\\s", 2);

            kv.put(vals[0], vals[1]);
        }

        return kv;
    }

    static String getInstructionType(String instParameters){

        String[] insString = instParameters.split("\\s", 2);

        return insString[0];
    }

    public static String getInstructionParameters(String instParameters){

        String[] insString = instParameters.split("\\s", 2);

        return insString[1];
    }



}
