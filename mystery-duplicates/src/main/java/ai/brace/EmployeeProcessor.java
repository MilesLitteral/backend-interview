package ai.brace;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class EmployeeProcessor
{
    public static Path getPathForResource(final String path)
    {
        try
        {
            return Paths.get(ClassLoader.getSystemResource(path).toURI());
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEntryUniqueInArray(final String value,final String[] intArray) {            
        int occurs = 0;     
        for (int i = 0; i < intArray.length && occurs < 2; i++) {         
            if (value == intArray[i]) {      
                    occurs++;               
            }                  
        }             
        return occurs == 1;       
    }   

    public static void main(String[] args)
    {
        final Map<Employee, Integer> duplicateCount = new HashMap<>();

        try (Stream<String> stream = Files.lines(getPathForResource("employees.csv")))
        {
            stream.forEach(line -> {
                final String[] elements = line.split(",");
                final Employee emp = new Employee(elements[0], elements[1], elements[2], elements[3]);
                S
                final Employee comparison = new Employee();
                if(emp.socialSecurityNumber == comparison && emp.lastName == elements[0])
                {
                    duplicateCount.put(emp, duplicateCount.getOrDefault(emp, 1) + 1);
                }
                else if(emp.socialSecurityNumber != comparison)
                {
                    duplicateCount.put(emp, duplicateCount.getOrDefault(emp, 0) + 1);
                }
                else{
                    duplicateCount.put(emp, duplicateCount.getOrDefault(emp, 0) + 1);
                }
            });
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }

        int finalEntry = 1;
        for (Map.Entry<Employee, Integer> entry : duplicateCount.entrySet())
        {
            final Employee emp = entry.getKey();
            System.out.println(emp.firstName + " " + emp.middleInitial + " " + emp.lastName + ": " + entry.getValue());
        }
    }
}
