package metridoc.rid

import org.apache.poi.ss.util.CellReference
import org.codehaus.groovy.grails.web.servlet.FlashScope
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

import org.apache.poi.ss.usermodel.*

class SpreadsheetUploadingService {

    def checkSpreadsheetFormat(MultipartFile uploadedFile) {
        Workbook wb = WorkbookFactory.create(uploadedFile.inputStream)
        Sheet sheet = wb.getSheetAt(0)
        int colNum = 1

        List<String> itemNames = new ArrayList<String>()
        for (int rowNum = 5; rowNum < 40; rowNum += 2) {
            Row row = sheet.getRow(rowNum)
            if (!row) return false
            Cell cell = row.getCell(colNum)
            if (!cell) return false

            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    itemNames.add(cell.getRichStringCellValue().getString())
                    break
                default:
                    System.out.println("CELL_TYPE_DEFAULT")
                    return false
            }
        }

        List<String> validNames = Arrays.asList('Report Type', 'Date of Consultation (mm/dd/yyyy)', 'Staff Pennkey',
                'Consultation Mode', 'Service Provided', 'User Goal', 'Prep Time (enter in minutes)',
                'Event Length (enter in minutes)', 'User Rank', 'User Affiliation', 'Interact Times', 'Course Name',
                'Department Affiliation', 'Course Number', 'Faculty Sponsor', 'Course Sponsor', 'User Question',
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
            for (int rowNum = 5; rowNum < 40; rowNum += 2) {
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
                        instance.add(cell.getCellFormula())
                        break
                    default:
                        flash.alerts << "Undefined Cell Type at " + new CellReference(rowNum, colNum).formatAsString()
                        System.out.println("CELL_TYPE_DEFAULT")
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
                    if (instance.get(i).empty) {
                        flash.alerts << "Report Type Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidReportType.findByName(instance.get(i))) {
                        flash.alerts << "Invalid Report Type at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 1:
                    if (instance.get(i).empty) {
                        flash.alerts << "Date of Consultation Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        new SimpleDateFormat("MM/dd/yyyy").parse(instance.get(i))
                    } catch (Exception e) {
                        flash.alerts << "Invalid Date Format at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 2:
                    if (instance.get(i).empty) {
                        flash.alerts << "Stuff Pennkey Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (instance.get(i).length() > 100) {
                        flash.alerts << "Stuff Pennkey Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 3:
                    if (instance.get(i).empty) {
                        flash.alerts << "Mode of Consultation Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidModeOfConsultation.findByName(instance.get(i))) {
                        flash.alerts << "Invalid Mode of Consultation at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 4:
                    if (instance.get(i).empty) {
                        flash.alerts << "Service Provided Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidServiceProvided.findByName(instance.get(i))) {
                        flash.alerts << "Invalid Service Provided at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 5:
                    if (!instance.get(i).empty && !RidUserGoal.findByName(instance.get(i))) {
                        flash.alerts << "Invalid User Goal at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 6:
                    if (instance.get(i).empty) {
                        flash.alerts << "Prep Time Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        if (Integer.valueOf(instance.get(i)) < 0) {
                            flash.alerts << "Negative Prep Time at " + cellRef.formatAsString()
                            return false
                        }
                        if (Integer.valueOf(instance.get(i)) > 50) {
                            flash.alerts << "Prep Time Too Large at " + cellRef.formatAsString()
                            return false
                        }
                    } catch (Exception e) {
                        flash.alerts << "Invalid Format for Prep Time at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 7:
                    if (instance.get(i).empty) {
                        flash.alerts << "Event Length Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        if (Integer.valueOf(instance.get(i)) < 0) {
                            flash.alerts << "Negative Event Length at " + cellRef.formatAsString()
                            return false
                        }
                        if (Integer.valueOf(instance.get(i)) > 50) {
                            flash.alerts << "Event Length Too Large at " + cellRef.formatAsString()
                            return false
                        }
                    } catch (Exception e) {
                        flash.alerts << "Invalid Format for Event Length at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 8:
                    if (instance.get(i).empty) {
                        flash.alerts << "User Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidUser.findByName(instance.get(i))) {
                        flash.alerts << "Invalid User at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 9:
                    if (instance.get(i).empty) {
                        flash.alerts << "User Affiliation Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidUserAffiliation.findByName(instance.get(i))) {
                        flash.alerts << "Invalid User Affiliation at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 10:
                    if (instance.get(i).empty) {
                        flash.alerts << "Interact Times Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    try {
                        if (Integer.valueOf(instance.get(i)) < 0) {
                            flash.alerts << "Negative Interact Times at " + cellRef.formatAsString()
                            return false
                        }
                        if (Integer.valueOf(instance.get(i)) > 50) {
                            flash.alerts << "Interact Times Too Large at " + cellRef.formatAsString()
                            return false
                        }
                    } catch (Exception e) {
                        flash.alerts << "Invalid Format for Interact Times at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 11:
                    if (instance.get(i).length() > 100) {
                        flash.alerts << "Course Name Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 12:
                    if (instance.get(i).empty) {
                        flash.alerts << "Departmental Affiliation Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidDepartmentalAffiliation.findByName(instance.get(i))) {
                        flash.alerts << "Invalid Departmental Affiliation at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 13:
                    if (instance.get(i).length() > 100) {
                        flash.alerts << "Course Number Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 14:
                    if (instance.get(i).length() > 100) {
                        flash.alerts << "Faculty Sponsor Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 15:
                    if (instance.get(i).empty) {
                        flash.alerts << "Course Sponsor Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (!RidCourseSponsor.findByName(instance.get(i))) {
                        flash.alerts << "Invalid Course Sponsor at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 16:
                    if (instance.get(i).empty) {
                        flash.alerts << "User Question Cannot be Empty at " + cellRef.formatAsString()
                        return false
                    }
                    if (instance.get(i).length() > 500) {
                        flash.alerts << "User Question Too Long at " + cellRef.formatAsString()
                        return false
                    }
                    break
                case 17:
                    if (instance.get(i).length() > 500) {
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
            def t = new RidTransaction(staffPennkey: instance.get(2), userQuestion: instance.get(16),
                    dateOfConsultation: new SimpleDateFormat("MM/dd/yyyy").parse(instance.get(1)),
                    interactTimes: Integer.valueOf(instance.get(10)).intValue(),
                    prepTime: Integer.valueOf(instance.get(6)).intValue(),
                    eventLength: Integer.valueOf(instance.get(7)).intValue(),
                    notes: instance.get(17), facultySponsor: instance.get(14), courseName: instance.get(11),
                    courseNumber: instance.get(13), //librarian: "librarian", patronEmail: "sample@gmail.com",
                    departmentalAffilication: RidDepartmentalAffiliation.findByName(instance.get(12)),
                    courseSponsor: RidCourseSponsor.findByName(instance.get(15)),
                    userGoal: RidUserGoal.findByName(instance.get(5)),
                    modeOfConsultation: RidModeOfConsultation.findByName(instance.get(3)),
                    user: RidUser.findByName(instance.get(8)),
                    serviceProvided: RidServiceProvided.findByName(instance.get(4)),
                    userAffiliation: RidUserAffiliation.findByName(instance.get(9)),
                    ridReportType: RidReportType.findByName(instance.get(0)),
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
}
