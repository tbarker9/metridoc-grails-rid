package metridoc.rid

import java.text.SimpleDateFormat

class RidStatisticsService {

    def getStats(Map params, String transType) {

        def results = new RidStatisticsReport()
        def query = RidConsTransaction.where {
            id >= 0
        }
        results.totalTransactions = query.count()

        query = RidConsTransaction.where {
            id >= 0
        }

        try {
            Date begin = new Date().minus(365);
            Date end = new Date()
            query = query.where {
                dateOfConsultation >= begin && dateOfConsultation < end.next()
            }
        } catch (Exception e) {}
        results.yearTransactions = query.count()

        query = RidConsTransaction.where {
            id >= 0
        }

        try {
            Date begin = new Date().minus(30);
            Date end = new Date()
            query = query.where {
                dateOfConsultation >= begin && dateOfConsultation < end.next()
            }
        } catch (Exception e) {}
        results.monthTransactions = query.count()

        query = RidConsTransaction.where {
            id >= 0
        }

        def interactSum = 0
        for (i in query) {
            interactSum += i.interactOccurrences
        }
        results.avgInteractOccurrences = interactSum / query.count()


        def prepSum = 0
        for (i in query) {
            prepSum += i.prepTime
        }
        results.avgPrepTime = prepSum / query.count()


        def eventSum = 0
        for (i in query) {
            eventSum += i.eventLength
        }
        results.avgEventLength = eventSum / query.count()

        def pklist = query.list { order("staffPennkey") }
        def testCount = pklist.countBy { it.staffPennkey }
        def tempKey = ""
        def tempCount = 0
        def bestKey = ""
        def bestCount = 0
        for (i in testCount) {
            if (i.value > bestCount && !i.key.equals(null) && !i.key.toString().equals("")) {
                bestCount = i.value
                bestKey = i.key.toString()
            }
        }
        results.staffPennkey = bestKey
        results.pennkeyMax = bestCount

        def dptlist = query.list { order("department") }
        testCount = dptlist.countBy { it.department }
        def tempDept = ""
        tempCount = 0
        def bestDept = ""
        bestCount = 0
        for (i in testCount) {
            if (i.value > bestCount && !i.key.equals(null) && !i.key.toString().equals("")) {
                bestCount = i.value
                bestDept = i.key.toString()
            }
        }
        results.department = bestDept
        results.departmentCount = bestCount

        def cnlist = query.list { order("courseName") }
        testCount = dptlist.countBy { it.courseName }
        def tempCourse = ""
        tempCount = 0
        def bestCourse = ""
        bestCount = 0
        for (i in testCount) {
            if (i.value > bestCount && !i.key.equals(null) && !i.key.toString().equals("")) {
                bestCount = i.value
                bestCourse = i.key.toString()
            }
        }
        results.courseName = bestCourse
        results.courseMax = bestCount

        def rlist = query.list { order("rank") }
        rlist.removeAll { it.rank.toString().equals("") || it.rank.equals(null) }
        results.rank = rlist.countBy { it.rank }

        def ulist = query.list { order("userGoal") }
        ulist.removeAll { it.userGoal.toString().equals("") || it.userGoal.equals(null) }
        results.userGoal = ulist.countBy { it.userGoal }

        def mlist = query.list { order("modeOfConsultation") }
        mlist.removeAll { it.modeOfConsultation.toString().equals("") || it.modeOfConsultation.equals(null) }
        results.modeOfConsultation = mlist.countBy { it.modeOfConsultation }

        def slist = query.list { order("school") }
        slist.removeAll { it.school.toString().equals("") || it.school.equals(null) }
        results.school = slist.countBy { it.school }

        def clist = query.list { order("courseSponsor") }
        clist.removeAll { it.courseSponsor.toString().equals("") || it.courseSponsor.equals(null) }
        results.courseSponsor = clist.countBy { it.courseSponsor }

        def splist = query.list { order("serviceProvided") }
        splist.removeAll { it.serviceProvided.toString().equals("") || it.serviceProvided.equals(null) }
        results.serviceProvided = splist.countBy { it.serviceProvided }

        def llist = query.list { order("ridLibraryUnit") }
        llist.removeAll { it.ridLibraryUnit.toString().equals("") || it.ridLibraryUnit.equals(null) }
        results.libraryUnit = llist.countBy { it.ridLibraryUnit }

        return results
    }

    def searchStats(Map params, String transType) {

        def results = new RidStatisticsSearchReport()


        if (params.ridLibraryUnitSearch.getClass() == String && !params.ridLibraryUnitSearch.equals("0")) {
            results.totalParams.add("ridLibraryUnit")
            results.totalNames.add(RidLibraryUnit.get(params.ridLibraryUnitSearch).toString())
        }
        if (params.staffPennkey.getClass() == String && !params.staffPennkey.equals("")) {
            results.totalParams.add("staffPennkey")
            results.totalNames.add(params.staffPennkey)
        }

        if (params.ridSchoolSearch.getClass() == String && !params.ridSchoolSearch.equals("0")) {
            results.totalParams.add("school")
            results.totalNames.add(RidSchool.get(params.ridSchoolSearch).toString())
        }

        if (params.ridDepartmentSearch.getClass() == String && !params.ridDepartmentSearch.equals("0")) {
            results.totalParams.add("department")
            results.totalNames.add(RidDepartment.get(params.ridDepartmentSearch).toString())
        }

        if (params.iterateAll) {
            iterateChoices(params, results.totalParams, results.inputs)
            iterateChoices(params, results.totalNames, results.inputNames)
        } else {
            results.inputs.add(results.totalParams)
            results.inputNames.add(results.totalNames)
        }

        for (i in results.inputs) {
            ArrayList temp = i
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

            if (temp.contains("ridLibraryUnit")) {
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

            if (temp.contains("staffPennkey")) {
                String[] staffPennkey_splits = params.staffPennkey.split(" ");
                for (String s in staffPennkey_splits) {
                    if (!s.trim().isEmpty()) {
                        query = query.where {
                            staffPennkey ==~ ~s.trim()
                        }
                    }
                }
            }

            if (temp.contains("school")) {
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

            if (temp.contains("department")) {
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

            if (query.count() > 0) {
                def interactSum = 0
                for (s in query) {
                    interactSum += s.interactOccurrences
                }
                results.avgInteractOccurrences.add(((double) (interactSum / query.count())).round(1))


                def prepSum = 0
                for (p in query) {
                    prepSum += p.prepTime
                }
                results.avgPrepTime.add(((double) (prepSum / query.count())).round(1))


                def eventSum = 0
                for (e in query) {
                    eventSum += e.eventLength
                }
                results.avgEventLength.add(((double) (eventSum / query.count())).round(1))
            }

            results.totalTransactions.add(query.count())


        }
        for (s in results.totalTransactions) {
            results.transactionSum += s
        }
        return results

    }

    def iterateChoices(Map params, ArrayList totalParams, ArrayList finished) {

        ArrayList temp = new ArrayList()

        if (totalParams.size() == 4) {
            finished.add(totalParams)
            temp.add(totalParams[0])
            temp.add(totalParams[1])
            temp.add(totalParams[2])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            temp.add(totalParams[1])
            temp.add(totalParams[3])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            temp.add(totalParams[2])
            temp.add(totalParams[3])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[1])
            temp.add(totalParams[2])
            temp.add(totalParams[3])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            temp.add(totalParams[1])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            temp.add(totalParams[2])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            temp.add(totalParams[3])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[1])
            temp.add(totalParams[2])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[1])
            temp.add(totalParams[3])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[2])
            temp.add(totalParams[3])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            finished.add(temp)
            temp = new ArrayList()
            temp.add(totalParams[1])
            finished.add(temp)
            temp = new ArrayList()
            temp.add(totalParams[2])
            finished.add(temp)
            temp = new ArrayList()
            temp.add(totalParams[3])
            finished.add(temp)

        } else if (totalParams.size() == 3) {
            finished.add(totalParams)

            temp = new ArrayList()
            temp.add(totalParams[0])
            temp.add(totalParams[1])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            temp.add(totalParams[2])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[1])
            temp.add(totalParams[2])
            finished.add(temp)

            temp = new ArrayList()
            temp.add(totalParams[0])
            finished.add(temp)
            temp = new ArrayList()
            temp.add(totalParams[1])
            finished.add(temp)
            temp = new ArrayList()
            temp.add(totalParams[2])
            finished.add(temp)

        } else if (totalParams.size() == 2) {
            finished.add(totalParams)
            temp = new ArrayList()
            temp.add(totalParams[0])
            finished.add(temp)
            temp = new ArrayList()
            temp.add(totalParams[1])
            finished.add(temp)
        } else {
            finished.add(totalParams)
        }
        return finished
    }
}
