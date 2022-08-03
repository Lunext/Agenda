/*package com.example.agenda.excel;

import com.example.agenda.models.Agenda;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportExcel {

    public List<Agenda> excelImport(){

        List<Agenda> listAgenda= new ArrayList<>();
        long id=0;
        String name="";
        String lastName="";
        String phoneNumber="";
        String email="";
        String country="";
        String city="";
        String identification="";

        String excelFilePath="C:\\Users\\euren\\Documents\\APEC\\Desarrollo con Open Source I\\AgendaForm.xlsx";

        long start= System.currentTimeMillis();

        FileInputStream inputStream= null;
        try {
            inputStream = new FileInputStream(excelFilePath);
            Workbook workbook= new XSSFWorkbook(inputStream);
            Sheet firstSheet= workbook.getSheetAt(0);
            Iterator<Row> rowIterator=firstSheet.iterator();
            rowIterator.next();

            while(rowIterator.hasNext()){
                Row nextRow= rowIterator.next();
                Iterator<Cell> cellIterator= nextRow.cellIterator();
                while(cellIterator.hasNext()){
                    Cell nextCell=cellIterator.next();
                    int columnIndex=nextCell.getColumnIndex();
                    switch(columnIndex){
                        case 0:
                            id=(long)nextCell.getNumericCellValue();
                            System.out.println(id);
                            break;
                        case 1:
                            name=(String)nextCell.getStringCellValue();
                            System.out.println(name);
                            break;
                        case 2:
                            lastName=(String)nextCell.getStringCellValue();
                            System.out.println(lastName);
                            break;
                        case 3:
                            phoneNumber=(String)nextCell.getStringCellValue();
                            System.out.println(phoneNumber);
                            break;
                        case 4:
                            email=(String)nextCell.getStringCellValue();
                            System.out.println(email);
                            break;

                        case 5:
                            country=(String)nextCell.getStringCellValue();
                            System.out.println(country);
                            break;

                        case 6:
                            city=(String)nextCell.getStringCellValue();
                            System.out.println(city);
                            break;

                        case 7:
                            identification=(String)nextCell.getStringCellValue();
                            System.out.println(identification);
                            break;

                    }
                    listAgenda.add(new Agenda(id,name,lastName,phoneNumber,email,country,city,identification));


                }
            }
            workbook.close();
            long end= System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n" , (end-start));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return listAgenda;

    }
}
*/