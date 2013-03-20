package metridoc.rid

import java.text.SimpleDateFormat

class RidTransactionService {

    def queryMethod(Map params) {
        def query = RidTransaction.where {
            template == Boolean.FALSE
        }

        try {
            Date start = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_start)
            Date end = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_end)
            query = query.where {
                dateOfConsultation >= start && dateOfConsultation < end.next()
            }
        } catch (Exception e) {
//            Date start = Date.parse("E MMM dd H:m:s z yyyy", params.dateOfConsultation_start)
//            Date end = Date.parse("E MMM dd H:m:s z yyyy", params.dateOfConsultation_end)
//            query = query.where {
//                dateOfConsultation >= start && dateOfConsultation < end.next()
//            }
        }

        def TypeList = params.list('ridReportTypeSearch')
        if (TypeList.size() > 0 && !TypeList.contains("0")) {
            List<Long> tList = new LinkedList<Long>()
            for (String id in TypeList)
                tList.add(Long.valueOf(id))
            query = query.where {
                ridReportType in RidReportType.findAllByIdInList(tList)
            }
        }

        String[] staffPennkey_splits = params.staffPennkey.split(" ");
        for (String s in staffPennkey_splits) {
            if (!s.trim().isEmpty()) {
                query = query.where {
                    staffPennkey ==~ ~s.trim()
                }
            }
        }

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

        String[] notes_splits = params.notes.split(" ");
        for (String s in notes_splits) {
            if (!s.trim().isEmpty()) {
                query = query.where {
                    notes ==~ ~s.trim()
                }
            }
        }

        return query
    }

    def ajaxMethod(Map params) {
        def userGoals = RidUserGoal.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 1)
        def consultations = RidModeOfConsultation.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 1)
        def services = RidServiceProvided.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 1)
        userGoals.addAll(RidUserGoal.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 2))
        consultations.addAll(RidModeOfConsultation.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 2))
        services.addAll(RidServiceProvided.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 2))
        if (!params.goalID.isEmpty()) {
            def goal = RidUserGoal.findByRidReportTypeAndId(RidReportType.get(params.typeId), params.goalID)
            if (goal != null && !userGoals.contains(goal))
                userGoals.add(0, goal)
        }
        if (!params.modeID.isEmpty()) {
            def mode = RidModeOfConsultation.findByRidReportTypeAndId(RidReportType.get(params.typeId), params.modeID)
            if (mode != null && !consultations.contains(mode))
                consultations.add(0, mode)
        }
        if (!params.serviceID.isEmpty()) {
            def service = RidServiceProvided.findByRidReportTypeAndId(RidReportType.get(params.typeId), params.serviceID)
            if (service != null && !userGoals.contains(service))
                services.add(0, service)
        }
        return ['userGoal': userGoals,
                'modeOfConsultation': consultations,
                'serviceProvided': services]
    }

    def createNewInstanceMethod(Map params, RidTransaction ridTransactionInstance) {
        String otherUser = params.otherUser
        //println(otherUser)
        if (otherUser != null && !otherUser.isEmpty()) {
            if (RidUser.findAllByName(otherUser).size() == 0) {
                def c = new RidUser(name: otherUser, inForm: 0)
                c.save()
                if (c.hasErrors()) println c.errors
            }
            if (RidUser.findAllByName(otherUser).size() > 0)
                ridTransactionInstance.user = RidUser.findByName(otherUser)
        }

        String otherUserAffiliation = params.otherUserAffiliation
        if (otherUserAffiliation != null && !otherUserAffiliation.isEmpty()) {
            if (RidUserAffiliation.findAllByName(otherUserAffiliation).size() == 0) {
                def e = new RidUserAffiliation(name: otherUserAffiliation, inForm: 0)
                e.save()
                if (e.hasErrors()) println e.errors
            }
            if (RidUserAffiliation.findAllByName(otherUserAffiliation).size() > 0)
                ridTransactionInstance.userAffiliation = RidUserAffiliation.findByName(otherUserAffiliation)
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
            if (RidModeOfConsultation.findAllByNameAndRidReportType(otherModeOfConsultation,
                    RidReportType.get(Long.valueOf(params.ridReportType.id))).size() == 0) {
                def s = new RidModeOfConsultation(name: otherModeOfConsultation, inForm: 0,
                        ridReportType: RidReportType.get(Long.valueOf(params.ridReportType.id)))
                s.save()
                if (s.hasErrors()) println s.errors
            }
            if (RidModeOfConsultation.findAllByNameAndRidReportType(otherModeOfConsultation,
                    RidReportType.get(Long.valueOf(params.ridReportType.id))).size() > 0)
                ridTransactionInstance.modeOfConsultation = RidModeOfConsultation.findByNameAndRidReportType(
                        otherModeOfConsultation, RidReportType.get(Long.valueOf(params.ridReportType.id)))
        }

        String otherService = params.otherService
        if (otherService != null && !otherService.isEmpty()) {
            if (RidServiceProvided.findAllByNameAndRidReportType(otherService,
                    RidReportType.get(Long.valueOf(params.ridReportType.id))).size() == 0) {
                def s = new RidServiceProvided(name: otherService, inForm: 0,
                        ridReportType: RidReportType.get(Long.valueOf(params.ridReportType.id)))
                s.save()
                if (s.hasErrors()) println s.errors
            }
            if (RidServiceProvided.findAllByNameAndRidReportType(otherService,
                    RidReportType.get(Long.valueOf(params.ridReportType.id))).size() > 0)
                ridTransactionInstance.serviceProvided = RidServiceProvided.findByNameAndRidReportType(otherService,
                        RidReportType.get(Long.valueOf(params.ridReportType.id)))
        }

        String otherUserGoal = params.otherUserGoal
        if (otherUserGoal != null && !otherUserGoal.isEmpty()) {
            if (RidUserGoal.findAllByNameAndRidReportType(otherUserGoal,
                    RidReportType.get(Long.valueOf(params.ridReportType.id))).size() == 0) {
                def s = new RidUserGoal(name: otherUserGoal, inForm: 0,
                        ridReportType: RidReportType.get(Long.valueOf(params.ridReportType.id)))
                s.save()
                if (s.hasErrors()) println s.errors
            }
            if (RidUserGoal.findAllByNameAndRidReportType(otherUserGoal,
                    RidReportType.get(Long.valueOf(params.ridReportType.id))).size() > 0)
                ridTransactionInstance.userGoal = RidUserGoal.findByNameAndRidReportType(otherUserGoal,
                        RidReportType.get(Long.valueOf(params.ridReportType.id)))
        }
    }
}
