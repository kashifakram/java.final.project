package POS18S2;

import java.util.*;

class Query {

    public static ArrayList<Query> queries = new ArrayList<>();

    private String queryType;
    private Resident resident;
    private String postCode;

    Query(){
        this.queryType = null;
        this.resident = null;
        this.postCode = null;
    }

    public void setQueryType(String queryType){
        this.queryType = queryType;
    }

    public void setResident(Resident resident){
        this.resident = resident;
    }

    public void setPostCode(String postCode){
        if(PosUtilities.isPostCodeValid(postCode))
            this.postCode = postCode;
    }

    @Override
    public String toString(){
        StringBuilder sb = null;
        if(queryType != null && queryType.equalsIgnoreCase("name")){
            sb = new StringBuilder();
            sb.append("----").append("query name ").append(resident.getName()).append("----\r\n");
            sb.append(resident.toString());
            sb.append("-------------------------\r\n");

            return sb.toString();
        } else if(queryType != null && queryType.equalsIgnoreCase("age")){

            Map<String, String> ageStatistics = Resident.getAgeStatisticsByPostCode(postCode);
            sb = new StringBuilder();
            sb.append("----")
                    .append("query ")
                    .append(postCode).append(" age----\r\n")
                    .append("Available pet owner size: ")
                    .append(ageStatistics.get("TotalOwners"))
                    .append("\r\n")
                    .append("Age profile:\r\n")
                    .append("Below 18: ")
                    .append(ageStatistics.get("UnderEighteen"))
                    .append("\r\n")
                    .append("Over 18 and Below 65: ")
                    .append(ageStatistics.get("BetweenEighteenAndSixtyFive"))
                    .append("\r\n")
                    .append("Over 65: ")
                    .append(ageStatistics.get("OverSixtyFive"))
                    .append("\r\n")
                    .append("Unknown: ")
                    .append(ageStatistics.get("Unknown"))
                    .append("\r\n")
                    .append("-------------------------\r\n");

            return sb.toString();

        } else if(queryType != null && queryType.equalsIgnoreCase("pet")){

            Map<String, Map<String, Integer>> table = Resident.getAllPetStatisticsByPostCode();
            sb = new StringBuilder();

            sb.append("----query pet----\r\n");

            for(Map.Entry<String, Map<String, Integer>> petInPostCode : table.entrySet()){
                String petName = petInPostCode.getKey();
                Integer maxPetCount = 0;
                List<String> maxPostCodes = new ArrayList<>();

                for(Map.Entry<String, Integer> entry : petInPostCode.getValue().entrySet()){
                    if(maxPetCount < entry.getValue()){
                        maxPostCodes.clear();
                        maxPostCodes.add(entry.getKey());
                        maxPetCount = entry.getValue();
                    }
                    else if (maxPetCount.equals(entry.getValue())){
                        maxPostCodes.add(entry.getKey());
                    }
                }
                        sb.append(petName)
                        .append(": ")
                        .append(maxPetCount)
                        .append("; ")
                        .append("postcode: ")
                        .append(maxPostCodes.toString().trim()
                                .replaceAll("[\\[]", "")
                                .replaceAll("]", ""))
                        .append("\r\n");

            }

            sb.append("-------------------------\r\n");

            return sb.toString();

        }

        return null;
    }

}
