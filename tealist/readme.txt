Application: TeaList
Version: 0.2

The applications lets you convert files with tea information between file formats and files.

Usage: java -jar TeaList.jar [OPTION][VALUE]

Options can be given in any order

Input/output format

-f from file format (requires value after)
-t to file format (require value after)

Input/output file

-i input file (requires value after)
-o output file (if no value is given standard output will be used)

Information

-l list avaliable file formats
-h print help

Example of valid options and values

java -jar TeaList.jar -h 
(prints help)

java -jar TeaList.jar -f text -t xml -i tea.txt 
(Reads a tealist in text format in tea.txt and writes it as xml to standard output)

java -jar TeaList.jar -o tea.txt -i tea.xml -t text -f xml
(Reads a tealist in xml format in tea.xml and writes it as text to tea.txt)