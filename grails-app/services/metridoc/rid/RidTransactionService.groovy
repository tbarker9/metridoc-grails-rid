package metridoc.rid

import org.apache.commons.collections.CollectionUtils

import java.text.SimpleDateFormat

class RidTransactionService {

    def queryMethod(Map params, String transType) {

        if (transType == "consultation") {
            def query = RidConsTransaction.where {
                id >= 0
            }

            if (params.dateOfConsultation_start && params.dateOfConsultation_end) {
                try {
                    Date start = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_start)
                    Date end = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_end)
                    query = query.where {
                        dateOfConsultation >= start && dateOfConsultation < end.next()
                    }
                } catch (Exception e) {}
            }

            if (params.ridLibraryUnitSearch) {
                def UnitList = params.list('ridLibraryUnitSearch')
                if (UnitList.size() > 0 && !UnitList.contains("0")) {
                    List<Long> uList = new LinkedList<Long>()
                    for (String id in UnitList)
                        uList.add(Long.valueOf(id))
                    query = query.where {
                        ridLibraryUnit in RidLibraryUnit.findAllByIdInList(uList)
                    }
                }
            }


            if (params.staffPennkey) {
                String[] staffPennkey_splits = params.staffPennkey.split(" ");
                for (String s in staffPennkey_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            staffPennkey ==~ ~s.trim()
                        }
                    }
                }
            }

            if (params.ridSchoolSearch) {
                def SchoolList = params.list('ridSchoolSearch')
                if (SchoolList.size() > 0 && !SchoolList.contains("0")) {
                    List<Long> sList = new LinkedList<Long>()
                    for (String id in SchoolList)
                        sList.add(Long.valueOf(id))
                    query = query.where {
                        school in RidSchool.findAllByIdInList(sList)
                    }
                }
            }

            if (params.ridDepartmentSearch) {
                def DepartmentList = params.list('ridDepartmentSearch')
                if (DepartmentList.size() > 0 && !DepartmentList.contains("0")) {
                    List<Long> dList = new LinkedList<Long>()
                    for (String id in DepartmentList)
                        dList.add(Long.valueOf(id))
                    query = query.where {
                        department in RidDepartment.findAllByIdInList(dList)
                    }
                }
            }

            if (params.userName) {
                String[] userName_splits = params.userName.split(" ");
                for (String s in userName_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            //userQuestion ==~ ~"^.+ba\$"
                            //userQuestion ==~ ~"^k.*"
                            userName ==~ ~s.trim()
                        }
                    }
                }
            }

            if (params.userQuestion) {
                String[] userQuestion_splits = params.userQuestion.split(" ");
                for (String s in userQuestion_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            //userQuestion ==~ ~"^.+ba\$"
                            //userQuestion ==~ ~"^k.*"
                            userQuestion ==~ ~s.trim()
                        }
                    }
                }
            }

            if (params.notes) {
                String[] notes_splits = params.notes.split(" ");
                for (String s in notes_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            notes ==~ ~s.trim()
                        }
                    }
                }
            }

            return query
        } else {
            def query = RidInsTransaction.where {
                id >= 0
            }

            if (params.dateOfInstruction_start && params.dateOfInstruction_end) {
                try {
                    Date start = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfInstruction_start)
                    Date end = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfInstruction_end)
                    query = query.where {
                        dateOfInstruction >= start && dateOfInstruction < end.next()
                    }
                } catch (Exception e) {
//            Date start = Date.parse("E MMM dd H:m:s z yyyy", params.dateOfConsultation_start)
//            Date end = Date.parse("E MMM dd H:m:s z yyyy", params.dateOfConsultation_end)
//            query = query.where {
//                dateOfConsultation >= start && dateOfConsultation < end.next()
//            }
                }
            }

            if (params.ridLibraryUnitSearch) {
                def UnitList = params.list('ridLibraryUnitSearch')
                if (UnitList.size() > 0 && !UnitList.contains("0")) {
                    List<Long> uList = new LinkedList<Long>()
                    for (String id in UnitList)
                        uList.add(Long.valueOf(id))
                    query = query.where {
                        ridLibraryUnit in RidLibraryUnit.findAllByIdInList(uList)
                    }
                }
            }

            if (params.instructorPennkey) {
                String[] instructorPennkey_splits = params.instructorPennkey.split(" ");
                for (String s in instructorPennkey_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            instructorPennkey ==~ ~s.trim()
                        }
                    }
                }
            }
            if (params.coInstructorPennkey) {
                String[] coInstructorPennkey_splits = params.coInstructorPennkey.split(" ");
                for (String s in coInstructorPennkey_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            coInstructorPennkey ==~ ~s.trim()
                        }
                    }
                }
            }

            if (params.ridDepartmentSearch) {
                def DepartmentList = params.list('ridDepartmentSearch')
                if (DepartmentList.size() > 0 && !DepartmentList.contains("0")) {
                    List<Long> dList = new LinkedList<Long>()
                    for (String id in DepartmentList)
                        dList.add(Long.valueOf(id))
                    query = query.where {
                        department in RidDepartment.findAllByIdInList(dList) || CollectionUtils.intersection(extraDepartments, RidDepartment.findAllByIdInList(dList)).size() > 0
                    }
                }
            }


            if (params.ridSchoolSearch) {
                def SchoolList = params.list('ridSchoolSearch')
                if (SchoolList.size() > 0 && !SchoolList.contains("0")) {
                    List<Long> sList = new LinkedList<Long>()
                    for (String id in SchoolList)
                        sList.add(Long.valueOf(id))
                    query = query.where {
                        school in RidSchool.findAllByIdInList(sList)
                    }
                }
            }
/*


            if (params.userName) {
                String[] userName_splits = params.userName.split(" ");
                for (String s in userName_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            //userQuestion ==~ ~"^.+ba\$"
                            //userQuestion ==~ ~"^k.*"
                            userName ==~ ~s.trim()
                        }
                    }
                }
            }
            */
            /*
            if (params.userQuestion) {
                String[] userQuestion_splits = params.userQuestion.split(" ");
                for (String s in userQuestion_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            //userQuestion ==~ ~"^.+ba\$"
                            //userQuestion ==~ ~"^k.*"
                            userQuestion ==~ ~s.trim()
                        }
                    }
                }
            }

            if (params.notes) {
                String[] notes_splits = params.notes.split(" ");
                for (String s in notes_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            notes ==~ ~s.trim()
                        }
                    }
                }
            }*/

            return query
        }
    }

    def ajaxMethod(Map params) {
        def userGoals = RidUserGoal.findAllByRidLibraryUnitAndInForm(RidLibraryUnit.get(params.typeId), 1)
        def consultations = RidModeOfConsultation.findAllByRidLibraryUnitAndInForm(RidLibraryUnit.get(params.typeId), 1)
        def services = RidServiceProvided.findAllByRidLibraryUnitAndInForm(RidLibraryUnit.get(params.typeId), 1)
        if (!params.goalID.isEmpty()) {
            def goal = RidUserGoal.findByRidLibraryUnitAndId(RidLibraryUnit.get(params.typeId), params.goalID)
            if (goal != null && !userGoals.contains(goal))
                userGoals.add(0, goal)
        }
        if (!params.modeID.isEmpty()) {
            def mode = RidModeOfConsultation.findByRidLibraryUnitAndId(RidLibraryUnit.get(params.typeId), params.modeID)
            if (mode != null && !consultations.contains(mode))
                consultations.add(0, mode)
        }
        if (!params.serviceID.isEmpty()) {
            def service = RidServiceProvided.findByRidLibraryUnitAndId(RidLibraryUnit.get(params.typeId), params.serviceID)
            if (service != null && !userGoals.contains(service))
                services.add(0, service)
        }
        userGoals.sort { it.name }.addAll(RidUserGoal.findAllByRidLibraryUnitAndInForm(RidLibraryUnit.get(params.typeId), 2))
        consultations.sort { it.name }.addAll(RidModeOfConsultation.findAllByRidLibraryUnitAndInForm(RidLibraryUnit.get(params.typeId), 2))
        services.sort { it.name }.addAll(RidServiceProvided.findAllByRidLibraryUnitAndInForm(RidLibraryUnit.get(params.typeId), 2))
        return ['userGoal': userGoals,
                'modeOfConsultation': consultations,
                'serviceProvided': services]
    }

    def createNewConsInstanceMethod(Map params, RidConsTransactionBase ridTransactionInstance) {
        String otherRank = params.otherRank
        if (otherRank != null && !otherRank.isEmpty()) {
            if (RidRank.findAllByName(otherRank).size() == 0) {
                def c = new RidRank(name: otherRank, inForm: 0)
                c.save()
                if (c.hasErrors()) println c.errors
            }
            if (RidRank.findAllByName(otherRank).size() > 0)
                ridTransactionInstance.rank = RidRank.findByName(otherRank)
        }

        String otherSchool = params.otherSchool
        if (otherSchool != null && !otherSchool.isEmpty()) {
            if (RidSchool.findAllByName(otherSchool).size() == 0) {
                def e = new RidSchool(name: otherSchool, inForm: 0)
                e.save()
                if (e.hasErrors()) println e.errors
            }
            if (RidSchool.findAllByName(otherSchool).size() > 0)
                ridTransactionInstance.school = RidSchool.findByName(otherSchool)
        }

        String otherCourseSponsor = params.otherCourseSponsor
        if (otherCourseSponsor != null && !otherCourseSponsor.isEmpty()) {
            if (RidCourseSponsor.findAllByName(otherCourseSponsor).size() == 0) {
                def c = new RidCourseSponsor(name: otherCourseSponsor, inForm: 0)
                c.save()
                if (c.hasErrors()) println c.errors
            }
            if (RidCourseSponsor.findAllByName(otherCourseSponsor).size() > 0)
                ridTransactionInstance.courseSponsor = RidCourseSponsor.findByName(otherCourseSponsor)
        }

        String otherModeOfConsultation = params.otherModeOfConsultation
        if (otherModeOfConsultation != null && !otherModeOfConsultation.isEmpty()) {
            if (RidModeOfConsultation.findAllByNameAndRidLibraryUnit(otherModeOfConsultation,
                    RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id))).size() == 0) {
                def s = new RidModeOfConsultation(name: otherModeOfConsultation, inForm: 0,
                        ridLibraryUnit: RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id)))
                s.save()
                if (s.hasErrors()) println s.errors
            }
            if (RidModeOfConsultation.findAllByNameAndRidLibraryUnit(otherModeOfConsultation,
                    RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id))).size() > 0)
                ridTransactionInstance.modeOfConsultation = RidModeOfConsultation.findByNameAndRidLibraryUnit(
                        otherModeOfConsultation, RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id)))
        }

        String otherService = params.otherService
        if (otherService != null && !otherService.isEmpty()) {
            if (RidServiceProvided.findAllByNameAndRidLibraryUnit(otherService,
                    RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id))).size() == 0) {
                def s = new RidServiceProvided(name: otherService, inForm: 0,
                        ridLibraryUnit: RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id)))
                s.save()
                if (s.hasErrors()) println s.errors
            }
            if (RidServiceProvided.findAllByNameAndRidLibraryUnit(otherService,
                    RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id))).size() > 0)
                ridTransactionInstance.serviceProvided = RidServiceProvided.findByNameAndRidLibraryUnit(otherService,
                        RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id)))
        }

        String otherUserGoal = params.otherUserGoal
        if (otherUserGoal != null && !otherUserGoal.isEmpty()) {
            if (RidUserGoal.findAllByNameAndRidLibraryUnit(otherUserGoal,
                    RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id))).size() == 0) {
                def s = new RidUserGoal(name: otherUserGoal, inForm: 0,
                        ridLibraryUnit: RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id)))
                s.save()
                if (s.hasErrors()) println s.errors
            }
            if (RidUserGoal.findAllByNameAndRidLibraryUnit(otherUserGoal,
                    RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id))).size() > 0)
                ridTransactionInstance.userGoal = RidUserGoal.findByNameAndRidLibraryUnit(otherUserGoal,
                        RidLibraryUnit.get(Long.valueOf(params.ridLibraryUnit.id)))
        }
    }

    def createNewInsInstanceMethod(Map params, RidInsTransactionBase ridTransactionInstance) {

        String otherLocation = params.otherLocation
        if (otherLocation != null && !otherLocation.isEmpty()) {
            if (RidLocation.findAllByName(otherLocation).size() == 0) {
                def e = new RidLocation(name: otherLocation, inForm: 0)
                e.save()
                if (e.hasErrors()) println e.errors
            }
            if (RidLocation.findAllByName(otherLocation).size() > 0)
                ridTransactionInstance.location = RidLocation.findByName(otherLocation)
        }
        String otherInstructionalMaterials = params.otherInstructionalMaterials
        if (otherInstructionalMaterials != null && !otherInstructionalMaterials.isEmpty()) {
            if (RidInstructionalMaterials.findAllByName(otherInstructionalMaterials).size() == 0) {
                def e = new RidInstructionalMaterials(name: otherInstructionalMaterials, inForm: 0)
                e.save()
                if (e.hasErrors()) println e.errors
            }
            if (RidInstructionalMaterials.findAllByName(otherInstructionalMaterials).size() > 0)
                ridTransactionInstance.instructionalMaterials = RidInstructionalMaterials.findByName(otherInstructionalMaterials)
        }
        String otherAudience = params.otherAudience
        if (otherAudience != null && !otherAudience.isEmpty()) {
            if (RidAudience.findAllByName(otherAudience).size() == 0) {
                def e = new RidAudience(name: otherAudience, inForm: 0)
                e.save()
                if (e.hasErrors()) println e.errors
            }
            if (RidAudience.findAllByName(otherAudience).size() > 0)
                ridTransactionInstance.audience = RidAudience.findByName(otherAudience)
        }
    }
}
