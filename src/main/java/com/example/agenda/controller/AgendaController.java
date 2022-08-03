package com.example.agenda.controller;

import com.example.agenda.excel.ExcelExporter;
import com.example.agenda.models.Agenda;
import com.example.agenda.services.AgendaService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@Controller
public class AgendaController {

    @Autowired
    private AgendaService service;




    @GetMapping({"agendas", "/"})
    public String listAgendas(Model model){




        model.addAttribute("agendas", service.listAgenda());
        return "agendas";
    }

    @GetMapping("agendas/new")
    public String createAgenda(Model model){

        Agenda a1= new Agenda();
        model.addAttribute("agenda",a1);

        return "create_agenda";
    }

    @PostMapping("agendas")
    public String saveAgenda(@ModelAttribute("agenda") Agenda agenda){

        service.saveAgenda(agenda);

        return "redirect:agendas";
    }

    @GetMapping("/agendas/edit/{id}")
    public String showEdit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("agenda", service.getAgendaById(id));
        return "edit_agenda";
    }

    @PostMapping("/agendas/{id}")
    public String updateAgenda(@PathVariable Integer id, @ModelAttribute("agenda") Agenda agenda, Model model){
        Agenda existingAgenda= service.getAgendaById(id);
        existingAgenda.setId(agenda.getId());
        existingAgenda.setName(agenda.getName());
        existingAgenda.setLastName(agenda.getLastName());
        existingAgenda.setPhoneNumber(agenda.getPhoneNumber());
        existingAgenda.setEmail(agenda.getEmail());
        existingAgenda.setCountry(agenda.getCountry());
        existingAgenda.setCity(agenda.getCity());
        existingAgenda.setIdentification(agenda.getIdentification());

        service.updateAgenda(existingAgenda);

        return "redirect:/agendas";
    }


    @GetMapping("/agendas/{id}")
    public String deleteLocal(@PathVariable Integer id){
        service.deleteAgenda(id);
        return "redirect:/agendas";
    }

    @GetMapping ("/agendas/importData/excel")
    public String ImportData() throws IOException {
        //Agenda existingAgenda= service.getAgendaById(Id);
        System.out.println("Execution started-- Opening Firefox browser.");
        System.setProperty("webdriver.gecko.driver", "D:\\Gecko\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.get("http://localhost:8080/agendas/new");
        FileInputStream fis= new FileInputStream("D:\\Excels\\Agendas.xlsx");
        XSSFWorkbook workbook= new XSSFWorkbook(fis);

        XSSFSheet sheet= workbook.getSheet("Agenda");
        int rowCount= sheet.getLastRowNum();
        int colCount= sheet.getRow(1).getLastCellNum();


        XSSFRow celldata= sheet.getRow(1);
        Long id=(long)celldata.getCell(7).getNumericCellValue();
        String name=celldata.getCell(0).getStringCellValue();
        String lastName=celldata.getCell(1).getStringCellValue();
        String phoneNumber=celldata.getCell(2).getStringCellValue();
        String email=celldata.getCell(3).getStringCellValue();
        String country=celldata.getCell(4).getStringCellValue();
        String city=celldata.getCell(5).getStringCellValue();
        String identification=celldata.getCell(6).getStringCellValue();
        driver.findElement(By.cssSelector("#name")).clear();
        driver.findElement(By.cssSelector("#name")).sendKeys(name);
        driver.findElement(By.cssSelector("#lastName")).clear();
        driver.findElement(By.cssSelector("#lastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#phoneNumber")).clear();
        driver.findElement(By.cssSelector("#phoneNumber")).sendKeys(phoneNumber);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys(email);
        driver.findElement(By.cssSelector("#country")).clear();
        driver.findElement(By.cssSelector("#country")).sendKeys(country);
        driver.findElement(By.cssSelector("#city")).clear();
        driver.findElement(By.cssSelector("#city")).sendKeys(city);
        driver.findElement(By.cssSelector("#identification")).clear();
        driver.findElement(By.cssSelector("#identification")).sendKeys(identification);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.id("save")).click();



        //driver.quit();
        System.out.println("Execution ending-- Webdriver session is closed.");
        return "redirect:/agendas";
    }

    @GetMapping("/agendas/export/excel")
    public void exportToExcel(HttpServletResponse response) throws  IOException{
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime= dateFormatter.format(new Date());

        String headerKey= "Content-Disposition";
        String headerValue= "attachment; filename=Agendas" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Agenda> listAgenda= service.listAgenda();

        ExcelExporter excelExporter= new ExcelExporter(listAgenda);
        excelExporter.export(response);
    }



}
