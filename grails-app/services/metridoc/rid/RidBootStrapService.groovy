package metridoc.rid

import grails.util.Environment
import org.apache.commons.lang.RandomStringUtils

class RidBootStrapService {

    def testDataService

    def bootStrapContents() {

        switch (Environment.current) {
            case Environment.DEVELOPMENT:

                if (!RidConsTransaction.first()) {
                    println "Creating test data for RID database"
                    RidConsTransaction.withNewTransaction {
                        testDataService.populateTestFields()

                        // ---------------------------------------------------------------------------------------------
                        // for ridTransaction (only for demo)
                        for (int i = 0; i < 25; i++) {
                            def t = new RidConsTransaction(staffPennkey: "012345667",
                                    userQuestion: RandomStringUtils.randomAlphanumeric(i % 50 + 1),
                                    interactOccurrences: i % 50, prepTime: i % 40,
                                    eventLength: i % 50, notes: "Sample Notes",
                                    facultySponsor: "Sample Sponsor", courseName: "Sample Course Name",
                                    courseNumber: "LIB001", dateOfConsultation: new Date(),
                                    department: RidDepartment.get(i % 6 + 1),
                                    courseSponsor: RidCourseSponsor.get(1),
                                    userGoal: RidUserGoal.findByRidLibraryUnit(RidLibraryUnit.get(1)),
                                    modeOfConsultation: RidModeOfConsultation.findByRidLibraryUnit(RidLibraryUnit.get(1)),
                                    rank: RidRank.get(1),
                                    serviceProvided: RidServiceProvided.findByRidLibraryUnit(RidLibraryUnit.get(1)),
                                    school: RidSchool.get(1),
                                    ridLibraryUnit: RidLibraryUnit.get(1 % 6 + 1)
                            )

                            try {
                                if (!t.save()) {
                                    if (t.hasErrors()) println t.errors
                                }
                            } catch (Exception e) {
                                log.error("an error occurred during bootstrap that will crash the entire application")
                                throw e
                            }
                        }
                    }
                }

                if (!RidInsTransaction.first()) {
                    //if (RidConsTransaction.first()) {
                    //    testDataService.populateTestFields()
                    //}
                    println "Creating test data for RID database"
                    RidInsTransaction.withNewTransaction {
                        // ---------------------------------------------------------------------------------------------
                        // for ridTransaction (only for demo)
                        for (int i = 0; i < 20; i++) {
                            def rand = new Random()
                            def t = new RidInsTransaction(instructorPennkey: "012345667",
                                    eventLength: i % 50, notes: "Sample Notes",
                                    sessionDescription: "Sample description",
                                    facultySponsor: "Sample Sponsor", courseName: "Sample Course Name",
                                    courseNumber: "LIB001", dateOfInstruction: new Date(),
                                    //department: RidDepartment.get(i % 6 + 1),
                                    sessionType: RidSessionType.get(1),
                                    instructionalMaterials: RidInstructionalMaterials.get(1),
                                    audience: RidAudience.get(1),
                                    school: RidSchool.get(1),
                                    location: RidLocation.get(1 % 3 + 1),
                                    attendanceTotal: 42,
                                    ridLibraryUnit: RidLibraryUnit.get(rand.nextInt(4) + 1)

                            )

                            try {
                                if (!t.save()) {
                                    println "\n"
                                    if (t.hasErrors()) println t.errors
                                    println "\n"
                                }
                            } catch (Exception e) {
                                log.error("an error occurred during bootstrap that will crash the entire application")
                                throw e
                            }
                        }
                    }
                }
                break

            case Environment.PRODUCTION:
                if (!RidLibraryUnit.first()) {
                    testDataService.populateTestFields()
                }
                break
        }
    }
}
