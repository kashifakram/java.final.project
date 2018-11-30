# java.final.project

SOFTWARE DESCRIPTION
 Read and validate the contents of TWO input text files (i.e. records file and instructions file), and
 Process the pet ownership records in the records file according to the instructions in the instructions file. The instructions should be performed sequentially (i.e. one after the other).
 When your program ends, your software saves the resulting records list (after being processed) to TWO output files (that are the results file containing the processed resident records list and the report file containing the query results).

DESCRIPTION AND FORMAT OF INPUT RESIDENT RECORDS
When your POS system starts up, it will firstly read in valid resident records in the input records file; and then process the valid resident records according to the instructions in the instructions file.
1. The input resident records file contains zero or multiple resident records in the predefined format:
 Separators: blank line(s) are used to separate the records.
 Invalid fields: fields with error(s). Invalid fields should be ignored and not read into the system.
 Invalid records: records missing any compulsory field or with invalid compulsory field. Invalid records will be discarded and will not be read and processed by your POS system.

2. An input resident record consists of 6 possible fields to specify necessary information of a resident and his/her pet ownership. There are 3 compulsory fields, i.e., name, phone and postcode; and the rest three fields are optional.
1) name (compulsory): resident’s name, in the form of a string of forename(s) and surname, all along one line. A name cannot include numeric and punctuation characters.
2) birthday: resident’s birthday, in the format of “(d)d-(m)m-(yy)yy”. Example: 11-07-2014 or 2-2-14.
3) address: is a string that may span more than one line, and must include the suburb.
4) postcode (compulsory): in the form of 4 numeric digits.
5) phone (compulsory): in the form of a sequence of 8 numeric digits. Leading zeros cannot be ignored.
6) pet: type(s) of pet(s), for instance, dog, cat, bird, etc. Different types of pets owned by one resident are spaned in the same line while separated by white space(s).

3. Format
 Compulsory fields are required for all records when they are read in from the input records file. That is, you may have a resident record with name, phone and postcode, or another record with all six fields.
 Each field begins on a new line and starts with the field name.
 Single/Multiple white spaces are used to separate field name and the field value/content.
 Fields may occur in any order.
 Example: "residentsample1.txt" is a sample resident record file, with records as below:
name Josephine Esmerelda Bloggs
birthday 13-05-1980
phone 99887766
pet Cat Dog
address 102 Smith St, Summer hill, NSW
postcode 2130
name Pac Man
birthday 07-3-1992
address 27 New Street, Elwood,
VIC
postcode 3184
phone 00333976
DESCRIPTION AND FORMAT OF INPUT INSTRUCTIONS

1. The instructions file contains zero or multiple instructions:
 There are four possible instructions: “update”, “delete”, “sort” and “query”.
 Each instruction occurs on a new line.
 Each instruction begins with one of the instructions followed by a list of parameters.

2. Description of instructions
 Update a person/record to your resident record list
o For instance, the instruction
update name Jo Bloggs; birthday 08-07-1998; phone 88884444; address 9001 Chester Crescent, Chatswood, NSW; postcode 2057
is supposed to add a record to your list for a resident with name "Jo Bloggs", birthday 08-07-1998, phone number 88884444, address "9001 Chester Crescent, Chatswood, NSW" and postcode 2057. Note the use of a semicolon (";") to separate the fields in the instruction.
o Your system will check whether this is an existing record:
o if the resident name and phone number are identical to those of an existing record, the identical existing record will be updated by the new information from the new record. E.g., update or merge the fields with the given values;
o otherwise a new valid record needs to be automatically created and added to your list by your program.
 Delete a resident from your list by name + phone
o For instance, the instruction
delete Jeff Vader; 04234567
indicates deleting the record with name “Jeff Vader” and phone number 04234567.
 Sort the records and save the results to resultsFile.
o The instruction
sort name
indicates: sort the residents by name in ascending order. If there are more than one resident having the same name, sort the residents with same name in ascending order of phone numbers. Note: you are required to implement a sorting method rather than directly invoking an available function from a Java API.
 Query the statistics using the following instructions and save the query results to reportFile. Query results from different queries should be separated using dash lines (---).
(1) Query the records by name and save all the query results to the report file in ascending order of phone numbers. The format of the query instruction is listed as below:
o query name David Joans
(2) Query the age statistics of pet owners in a suburb and save the query results to reportFile. The query on statistics is in the format of: postcode followed by a keyword age. For example,
o query 2057 age
indicates: query the age statistics of pet owners of a given suburb with postcode 2057. The query results as below should be appended to reportFile:

------query 2057 age------
Available pet owner size: 100
Age profile
Below 18: 20%
Over 18 and Below 65: 50%
Over 65: 30%
Unknown: 0
---------------------------

(3) Query the statistics of pet popularity and save the query results to reportFile. The format is:
o query pet
indicates: query the suburb(s) with the largest number of each type of pet existing in the residents records. For instance, if there are four types of pets which are dog, cat, bird and reptile existing in the residents’ records, the results as below should be appended to reportFile:
------query pet------
Dog: 70; postcode 2050
Cat: 50; postcode 2018
Bird: 35; postcode 2000
Reptile: 10; postcode 2006, 2050
---------------------

FORMAT OF OUTPUT FILES
1. Software needs to save the resulting data collection to the files including results file and report file.
2. The output files should have all the necessary resulting records.
3. Each field should fit on ONE line only.
4. Records should be separated by blank lines.
5. The format of output records should be consistent, e.g. fields are listed in same order and date format are consistent.
6. Report File: the results of different queries should be separated by a dash line with query instruction as shown in previous section.

SOFTWARE EXCUTION
Software must be executed using command line in the following format:
java POS18S2. POS recordFile instructionFile resultFile reportFile
 POS18s2 is the package containing your assignment software.
 POS is the name for the main class.
 Two input files specified by command line arguments:
o recordfile –containing the list of resident records
o instructionfile –containing the set of instructions/operations to be carried out by your software
 Two input files specified by command line arguments:
o resultfile – saving the processed resident records
o reportfile – saving the results from query commands
 DO NOT implement a graphical user interface (GUI). DO NOT hard code the path/name of the input/output files as they will change between runs of the program. Some example invocations using the Command Prompt:
java POS18s2.POS u:\records\rec01.txt u:\ins01.txt w:\out\res01.txt W:\report01.txt
java POS18s2.POS c:\rec03.txt d:\ins07.txt result.txt report.txt
IMPORTANT NOTES
1. Your code must make use of at least one collection e.g. ArrayList.
2. Your system must be able to handle both normal cases and difficult cases.
3. You MUST NOT build a graphical user interface.
4. You need to do systematic testing of the classes you create.
5. Your program must run on the computers in your lab classes, and must be demonstrated during your laboratory class to your tutor in week 10 and in week 12. Being absent from the demos will incur a penalty of 4 marks. If you miss the labs in weeks 10 and/or 12 because of serious illness or misadventure, you should request Special Consideration using the appropriate forms within a week.
6. Recall that the University takes plagiarism very seriously.
