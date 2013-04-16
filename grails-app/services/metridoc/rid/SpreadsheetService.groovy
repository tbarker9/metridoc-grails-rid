package metridoc.rid

import org.apache.poi.ss.util.CellReference
import org.codehaus.groovy.grails.web.servlet.FlashScope
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

import org.apache.poi.ss.usermodel.*
import org.codehaus.groovy.grails.io.support.ClassPathResource

class SpreadsheetService {

    def checkSpreadsheetFormat(MultipartFile uploadedFile) {
        Workbook wb = WorkbookFactory.create(uploadedFile.inputStream)
        Sheet sheet = wb.getSheetAt(0)
        int colNum = 1

        List<String> itemNames = new ArrayList<String>()
        for (int rowNum = 5; rowNum < 42; rowNum += 2) {
            Row row = sheet.getRow(rowNum)
            if (!row) return false
            Cell cell = row.getCell(colNum)
            if (!cell) return false

            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    itemNames.add(cell.getRichStringCellValue().getString())
                    break
                default:
                    println "CELL_TYPE_DEFAULT at " + new CellReference(rowNum, colNum).formatAsString()
                    return false
            }
        }

        List<String> validNames = Arrays.asList('Library Unit', 'Date of Consultation (mm/dd/yyyy)', 'Staff Pennkey',
                'Consultation Mode', 'Service Provided', 'User Goal', 'Prep Time (enter in minutes)',
                'Event Length (enter in minutes)', 'User Name', 'Rank', 'School', 'Interact Times', 'Course Name',
                'Department', 'Course Number', 'Faculty Sponsor', 'Course Sponsor', 'User Question',
                'Notes'
        )

        if (validNames.size() != itemNames.size()) return false
        for (int i = 0 ; i < itemNames.size(); i++) {
            if (!itemNames.get(i).trim().equals(validNames.get(i).trim())) return false
        }
        return true
    }

    def getInstancesFromSpreadsheet(MultipartFile uploadedFile, FlashScope flash) {
        Workbook wb = WorkbookFactory.create(uploadedFile.inputStream)
        Sheet sheet = wb.getSheetAt(0)
        int colNum = 1
        Boolean iterNext = Boolean.TRUE
        List<List<String>> allInstances = new ArrayList<ArrayList<String>>()

        while (iterNext && ++colNum) {
            List<String> instance = new ArrayList<String>()
            for (int rowNum = 5; rowNum < 42; rowNum += 2) {
                Row row = sheet.getRow(rowNum)
                if (!row) {
                    iterNext = Boolean.FALSE
                    break
                }
                Cell cell = row.getCell(colNum)
                if (!cell) {
                    if (rowNum == 5) {
                        iterNext = Boolean.FALSE
                        break
                    }
                    else {
                        instance.add("")
                        continue
                    }
                }

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        instance.add(cell.getRichStringCellValue().getString())
                        break
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            instance.add(cell.getDateCellValue().format("MM/dd/yyyy"))
                        } else {
                            instance.add(cell.getNumericCellValue().toInteger().toString())
                        }
                        break
                    case Cell.CELL_TYPE_FORMULA:
                        instance.add(cell.getCellFormula().trim())
                        break
                    case Cell.CELL_TYPE_BLANK:
                        instance.add("")
                        break
                    default:
                        flash.alerts << "Undefined Cell Type at " + new CellReference(rowNum, colNum).formatAsString()
                        println "CELL_TYPE_DEFAULT at " + new CellReference(rowNum, colNum).formatAsString()
                }
            }

            if (iterNext) {
                if (checkValid(instance, allInstances.size(), flash))
                    allInstances.add(instance)
                else {
                    allInstances.clear()
                    return allInstances
                }
            }
        }

        if (!allInstances.size())
            flash.alerts << "No Instance in the Spreadsheet Uploaded!"
        return allInstances
    }

    def checkValid(List<String> instance, int count, FlashScope flash) {
        for (int i = 0; i < instance.size(); i++) {
            CellReference cellRef = new CellReference(5 + i * 2, count + 2)
            switch (i) {
                case 0:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Library Unit Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidLibraryUnit.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid Library at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 1:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Date of Consultation Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        new SimpleDateFormat("MM/dd/yyyy").parse(instance.get(i).trim())
                    } catch (Exception e) {
                        flash.alerts << "Invalid Date Format at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 2:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Stuff Pennkey Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (instance.get(i).trim().length() > 100) {
                        flash.alerts << "Stuff Pennkey Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 3:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Mode of Consultation Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidModeOfConsultation.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid Mode of Consultation at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(
                            instance.get(i).trim(), RidLibraryUnit.findByName(instance.get(0).trim()))) {
                        flash.alerts << "Mode of Consultation at " + cellRef.formatAsString() +
                                " does NOT match the Report Type" + instance.get(0).trim()
                        return false
                    }
                    break
                case 4:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Service Provided Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidServiceProvided.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid Service Provided at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidServiceProvided.findByNameAndRidLibraryUnit(
                            instance.get(i).trim(), RidLibraryUnit.findByName(instance.get(0).trim()))) {
                        flash.alerts << "Service Provided at " + cellRef.formatAsString() +
                                " does NOT match the Report Type" + instance.get(0).trim()
                        return false
                    }
                    break
                case 5:
                    if (!instance.get(i).trim().empty && !RidUserGoal.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid User Goal at " + cellRef.formatAsString()
                        return false
                    }
                    if (!instance.get(i).trim().empty && !RidUserGoal.findByNameAndRidLibraryUnit(
                            instance.get(i).trim(), RidLibraryUnit.findByName(instance.get(0).trim()))) {
                        flash.alerts << "User Goal at " + cellRef.formatAsString() +
                                " does NOT match the Report Type" + instance.get(0).trim()
                        return false
                    }
                    break
                case 6:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Prep Time Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        if (Integer.valueOf(instance.get(i).trim()) < 0) {
                            flash.alerts << "Negative Prep Time at " + cellRef.formatAsString()
                            return false
                        }
                        if (Integer.valueOf(instance.get(i).trim()) > 50) {
                            flash.alerts << "Prep Time Too Large at " + cellRef.formatAsString()
                            return false
                        }
                    } catch (Exception e) {
                        flash.alerts << "Invalid Format for Prep Time at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 7:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Event Length Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        if (Integer.valueOf(instance.get(i).trim()) < 0) {
                            flash.alerts << "Negative Event Length at " + cellRef.formatAsString()
                            return false
                        }
                        if (Integer.valueOf(instance.get(i).trim()) > 50) {
                            flash.alerts << "Event Length Too Large at " + cellRef.formatAsString()
                            return false
                        }
                    } catch (Exception e) {
                        flash.alerts << "Invalid Format for Event Length at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 8:
                    if (instance.get(i).trim().length() > 50) {
                        flash.alerts << "User Name Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 9:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Rank Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidRank.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid Rank at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 10:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "School Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidSchool.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid School at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 11:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Interact Times Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        if (Integer.valueOf(instance.get(i).trim()) < 0) {
                            flash.alerts << "Negative Interact Times at " + cellRef.formatAsString()
                            return false
                        }
                        if (Integer.valueOf(instance.get(i).trim()) > 50) {
                            flash.alerts << "Interact Times Too Large at " + cellRef.formatAsString()
                            return false
                        }
                    } catch (Exception e) {
                        flash.alerts << "Invalid Format for Interact Times at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 12:
                    if (instance.get(i).trim().length() > 100) {
                        flash.alerts << "Course Name Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 13:
                    if (!instance.get(i).trim().empty && !RidDepartment.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid Department at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 14:
                    if (instance.get(i).trim().length() > 100) {
                        flash.alerts << "Course Number Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 15:
                    if (instance.get(i).trim().length() > 100) {
                        flash.alerts << "Faculty Sponsor Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 16:
                    if (instance.get(i).trim().empty) {
                        flash.alerts << "Course Sponsor Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidCourseSponsor.findByName(instance.get(i).trim())) {
                        flash.alerts << "Invalid Course Sponsor at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 17:
                    if (!instance.get(i).empty && instance.get(i).trim().length() > 500) {
                        flash.alerts << "User Question Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 18:
                    if (instance.get(i).trim().length() > 500) {
                        flash.alerts << "Notes Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                default:
                    return false
            }
        }
        return true
    }

    def checkFileType(String fileType) {
        List<String> supportedTypes = Arrays.asList(
                'application/vnd.ms-excel [official]', 'application/msexcel',
                'application/x-msexcel', 'application/x-ms-excel', 'application/vnd.ms-excel',
                'application/x-excel', 'application/x-dos_ms_excel', 'application/xls',
                'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
        )
        return supportedTypes.contains(fileType)
    }

    def saveToDatabase(List<List<String>> allInstances, String spreadsheetName, FlashScope flash) {
        for (List<String> instance in allInstances) {
            def type = RidLibraryUnit.findByName(instance.get(0))
            def t = new RidTransaction(staffPennkey: instance.get(2), userQuestion: instance.get(17),
                    dateOfConsultation: new SimpleDateFormat("MM/dd/yyyy").parse(instance.get(1)),
                    interactTimes: Integer.valueOf(instance.get(11)).intValue(),
                    prepTime: Integer.valueOf(instance.get(6)).intValue(),
                    eventLength: Integer.valueOf(instance.get(7)).intValue(),
                    notes: instance.get(18), facultySponsor: instance.get(15), courseName: instance.get(12),
                    courseNumber: instance.get(14), userName: instance.get(8),
                    department: RidDepartment.findByName(instance.get(13)),
                    courseSponsor: RidCourseSponsor.findByName(instance.get(16)),
                    userGoal: RidUserGoal.findByNameAndRidLibraryUnit(instance.get(5), type),
                    modeOfConsultation: RidModeOfConsultation.findByNameAndRidLibraryUnit(instance.get(3), type),
                    rank: RidRank.findByName(instance.get(9)),
                    serviceProvided: RidServiceProvided.findByNameAndRidLibraryUnit(instance.get(4), type),
                    school: RidSchool.findByName(instance.get(10)),
                    ridLibraryUnit: type,
                    spreadsheetName: spreadsheetName
            )

            try {
                if (!t.save(flush: true)) {
                    flash.alerts << t.errors
                    return false
                }
            } catch (Exception e) {
                flash.alerts << e.message
                return false
            }
        }
        return true
    }

    def exportAsFile(List<RidTransaction> ridTransactionList) {
        ClassPathResource resource = new ClassPathResource('spreadsheet/Transaction_List.xlsx')
        Workbook wb = WorkbookFactory.create(resource.getFile().newInputStream())
        Sheet sheet = wb.getSheetAt(0)

        CellStyle red_bold = wb.createCellStyle()
        Font ft = wb.createFont()
        ft.boldweight = Font.BOLDWEIGHT_BOLD
        ft.color = Font.COLOR_RED
        red_bold.font = ft

        int colNum = 2
        // for test subList(0,1)
        for (RidTransaction rid in ridTransactionList) {
            sheet.getRow(5).createCell(colNum).setCellValue(rid.ridLibraryUnit.name)
            sheet.getRow(5).getCell(colNum).setCellStyle(red_bold)
            sheet.getRow(7).createCell(colNum).setCellValue(rid.dateOfConsultation.format("MM/dd/yyyy"))
            sheet.getRow(9).createCell(colNum).setCellValue(rid.staffPennkey)
            sheet.getRow(11).createCell(colNum).setCellValue(rid.modeOfConsultation.name)
            sheet.getRow(13).createCell(colNum).setCellValue(rid.serviceProvided.name)
            sheet.getRow(15).createCell(colNum).setCellValue(rid.userGoal.name)
            sheet.getRow(17).createCell(colNum).setCellValue(String.valueOf(rid.prepTime))
            sheet.getRow(19).createCell(colNum).setCellValue(String.valueOf(rid.eventLength))
            sheet.getRow(21).createCell(colNum).setCellValue(rid.userName)
            sheet.getRow(23).createCell(colNum).setCellValue(rid.rank.name)
            sheet.getRow(25).createCell(colNum).setCellValue(rid.school.name)
            sheet.getRow(27).createCell(colNum).setCellValue(String.valueOf(rid.interactTimes))
            sheet.getRow(29).createCell(colNum).setCellValue(rid.courseName)
            sheet.getRow(31).createCell(colNum).setCellValue(rid.department.name)
            sheet.getRow(33).createCell(colNum).setCellValue(rid.courseNumber)
            sheet.getRow(35).createCell(colNum).setCellValue(rid.facultySponsor)
            sheet.getRow(37).createCell(colNum).setCellValue(rid.courseSponsor.name)
            sheet.getRow(39).createCell(colNum).setCellValue(rid.userQuestion)
            sheet.getRow(41).createCell(colNum).setCellValue(rid.notes)

            sheet.setColumnWidth(colNum, 4000);
            for (int r = 5; r < 42; r += 2)
                sheet.getRow(r).getCell(colNum).setCellType(Cell.CELL_TYPE_STRING)

            colNum++
        }

        return wb
    }
}
