package metridoc.rid

import java.text.SimpleDateFormat

import static java.lang.Math.abs
import static java.util.Calendar.MONTH
import static java.util.Calendar.YEAR

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
        for (i in query) {
            results.yearInteractOccurences += i.interactOccurrences
            results.yearEventLength += i.eventLength
            results.yearPrepTime += i.prepTime
        }


        for (int m = 12; m >= 0; m--) {
            query = RidConsTransaction.where {
                id >= 0
            }
            int mIO
            int mEL
            int mPT


            Date begin = new Date()
            Date end = new Date()
            int newMonth = begin.getAt(MONTH) - m
            int newYear = begin.getAt(YEAR)
            if (newMonth < 0) {
                newMonth += 12
                newYear -= 1
            }
            begin.set([date: 1, month: newMonth, year: newYear])
            newMonth += 1
            if (newMonth == 12) {
                newMonth = 0
                newYear += 1
            }

            end.set(date: 1, month: newMonth, year: newYear)
            query = query.where {
                dateOfConsultation >= begin && dateOfConsultation < end.next()
            }

            results.monthTransactions.add(query.count())
            results.months.add(begin)
            for (i in query) {
                mIO += i.interactOccurrences
                mEL += i.eventLength
                mPT += i.prepTime
            }
            results.monthInteractOccurences.add(mIO)
            results.monthEventLength.add(mEL)
            results.monthPrepTime.add(mPT)
        }
        query = RidConsTransaction.where {
            id >= 0
        }

        def interactSum = 0
        for (i in query) {
            interactSum += i.interactOccurrences
        }
        results.totalInteractOccurences = interactSum
        results.avgInteractOccurrences = interactSum / query.count()


        def prepSum = 0
        for (i in query) {
            prepSum += i.prepTime
        }
        results.totalPrepTime = prepSum
        results.avgPrepTime = prepSum / query.count()


        def eventSum = 0
        for (i in query) {
            eventSum += i.eventLength
        }
        results.totalEventLength = eventSum
        results.avgEventLength = eventSum / query.count()
        //Pennkey data not currently in statistics display
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

        //Cludgy code to remove Hibernate proxy classes
        def depts = RidDepartment.where { name != "" }.list() { order("name") }.countBy { it.name }

        for (d in depts) {
            results.departments.add(d.getKey())
        }


        for (t in results.departments) {
            results.totalDepartments.add(0)
            results.yearDepartments.add(0)
            results.monthDepartments.add(new ArrayList())
        }
        for (t in results.totalDepartments) {
            t = 0
        }
        for (t in results.yearDepartments) {
            t = 0
        }
        for (t in results.monthDepartments) {
            for (int i = 0; i <= 12; i++) {
                t.add(0)
            }
        }

        query = RidConsTransaction.where {
            id >= 0 && department != null
        }

        def dptList = query.list { order("department") }
        def dptCount = dptList.countBy { it.department.name }

        for (each in dptCount) {
            if (each.getKey() != "" && each.getKey() != null) {
                int index = results.departments.indexOf(each.getKey())
                results.totalDepartments[index] = each.getValue()
            }
        }


        for (int m = 12; m >= 0; m--) {
            query = RidConsTransaction.where {
                id >= 0
            }

            Date begin = new Date()
            Date end = new Date()
            int newMonth = begin.getAt(MONTH) - m
            int newYear = begin.getAt(YEAR)
            if (newMonth < 0) {
                newMonth += 12
                newYear -= 1
            }
            begin.set([date: 1, month: newMonth, year: newYear])
            newMonth += 1
            if (newMonth == 12) {
                newMonth = 0
                newYear += 1
            }

            end.set(date: 1, month: newMonth, year: newYear)
            query = query.where {
                dateOfConsultation >= begin && dateOfConsultation < end.next() && department != null
            }

            dptList = query.list { order("department") }
            dptCount = dptList.countBy { it.department.name }

            for (int d = 0; d < results.departments.size(); d++) {
                if (dptCount.containsKey(results.departments[d].toString())) {
                    results.yearDepartments[d] += dptCount.get(results.departments[d])
                    results.monthDepartments[d][abs(m - 12)] += (dptCount.get(results.departments[d]))
                }

            }
        }

        def maxValues = new ArrayList()
        def maxIndices = new ArrayList()

        def tempDepts = new ArrayList()
        tempDepts.addAll(results.departments)
        def tempTotals = new ArrayList()
        tempTotals.addAll(results.totalDepartments)
        def tempYear = new ArrayList()
        tempYear.addAll(results.yearDepartments)
        def tempMonth = new ArrayList()
        tempMonth.addAll(results.monthDepartments)

        for (int v = 0; v < 5; v++) {

            maxValues[v] = tempTotals.toArray().max()
            maxIndices[v] = tempTotals.indexOf(maxValues[v])
            results.topFiveDepartments.add(tempDepts[maxIndices[v]])
            results.topFiveTotalDepartments.add(tempTotals[maxIndices[v]])
            results.topFiveYearDepartments.add(tempYear[maxIndices[v]])
            results.topFiveMonthDepartments.add(tempMonth[maxIndices[v]])

            tempDepts.remove(maxIndices[v])
            tempTotals.remove(maxIndices[v])
            tempYear.remove(maxIndices[v])
            tempMonth.remove(maxIndices[v])
        }

        //Courses

        //Cludgy code to remove Hibernate proxy classes

        def allCourses = RidConsTransaction.where {
            id >= 0 && courseName != "" && courseName != null
        }.list() { order("courseName") }.countBy { it.courseName }

        for (d in allCourses) {
            if (d.getValue() != null) {
                results.courses.add(d.getKey())
            }
        }


        for (t in results.courses) {
            results.coursesAdded++
            results.totalCourses.add(0)
            results.yearCourses.add(0)
            results.monthCourses.add(new ArrayList())
        }

        for (t in results.totalCourses) {
            t = 0
        }
        for (t in results.yearCourses) {
            t = 0
        }
        for (t in results.monthCourses) {
            for (int i = 0; i <= 12; i++) {
                t.add(0)
            }
        }

        for (each in allCourses) {
            if (each.getKey() != "" && each.getKey() != null && each.getValue() != null) {
                int index = results.courses.indexOf(each.getKey())
                results.totalCourses[index] = each.getValue()
            }
        }


        for (int m = 12; m >= 0; m--) {
            query = RidConsTransaction.where {
                id >= 0
            }

            Date begin = new Date()
            Date end = new Date()
            int newMonth = begin.getAt(MONTH) - m
            int newYear = begin.getAt(YEAR)
            if (newMonth < 0) {
                newMonth += 12
                newYear -= 1
            }
            begin.set([date: 1, month: newMonth, year: newYear])
            newMonth += 1
            if (newMonth == 12) {
                newMonth = 0
                newYear += 1
            }

            end.set(date: 1, month: newMonth, year: newYear)
            query = query.where {
                dateOfConsultation >= begin && dateOfConsultation < end.next()
            }
            allCourses = query.where {
                id >= 0 && courseName != "" && courseName != null
            }.list() { order("courseName") }.countBy { it.courseName }
            for (map in allCourses) {
                if (map.getValue() == null) {
                    allCourses.remove(map)
                }
            }
            for (int d = 0; d < results.courses.size(); d++) {
                if (allCourses.containsKey(results.courses[d].toString())) {
                    results.yearCourses[d] += allCourses.get(results.courses[d])
                    results.monthCourses[d][abs(m - 12)] += (allCourses.get(results.courses[d]))
                }

            }
        }

        maxValues = new ArrayList()
        maxIndices = new ArrayList()

        def tempCourses = new ArrayList()
        tempCourses.addAll(results.courses)
        tempTotals = new ArrayList()
        tempTotals.addAll(results.totalCourses)
        tempYear = new ArrayList()
        tempYear.addAll(results.yearCourses)
        tempMonth = new ArrayList()
        tempMonth.addAll(results.monthCourses)
        def xOutOfFive
        if (results.coursesAdded < 5) {
            xOutOfFive = results.coursesAdded
        } else {
            xOutOfFive = 5
        }

        for (int v = 0; v < xOutOfFive; v++) {

            maxValues[v] = tempTotals.toArray().max()
            maxIndices[v] = tempTotals.indexOf(maxValues[v])
            results.topFiveCourses.add(tempCourses[maxIndices[v]])
            results.topFiveTotalCourses.add(tempTotals[maxIndices[v]])
            results.topFiveYearCourses.add(tempYear[maxIndices[v]])
            results.topFiveMonthCourses.add(tempMonth[maxIndices[v]])

            tempCourses.remove(maxIndices[v])
            tempTotals.remove(maxIndices[v])
            tempYear.remove(maxIndices[v])
            tempMonth.remove(maxIndices[v])
        }

//Ranks

//Cludgy code to remove Hibernate proxy classes
        def allRanks = RidRank.where { name != "" }.list() { order("name") }.countBy { it.name }

        for (d in allRanks) {
            results.ranks.add(d.getKey())
        }


        for (t in results.ranks) {
            results.totalRanks.add(0)
            results.yearRanks.add(0)
            results.monthRanks.add(new ArrayList())
        }
        for (t in results.totalRanks) {
            t = 0
        }
        for (t in results.yearRanks) {
            t = 0
        }
        for (t in results.monthRanks) {
            for (int i = 0; i <= 12; i++) {
                t.add(0)
            }
        }

        query = RidConsTransaction.where {
            id >= 0 && rank != null
        }

        def rankList = query.list { order("rank") }
        def rankCount = rankList.countBy { it.rank.name }

        for (each in rankCount) {
            if (each.getKey() != "" && each.getKey() != null) {
                int index = results.ranks.indexOf(each.getKey())
                results.totalRanks[index] = each.getValue()
            }
        }


        for (int m = 12; m >= 0; m--) {
            query = RidConsTransaction.where {
                id >= 0
            }

            Date begin = new Date()
            Date end = new Date()
            int newMonth = begin.getAt(MONTH) - m
            int newYear = begin.getAt(YEAR)
            if (newMonth < 0) {
                newMonth += 12
                newYear -= 1
            }
            begin.set([date: 1, month: newMonth, year: newYear])
            newMonth += 1
            if (newMonth == 12) {
                newMonth = 0
                newYear += 1
            }

            end.set(date: 1, month: newMonth, year: newYear)
            query = query.where {
                dateOfConsultation >= begin && dateOfConsultation < end.next() && rank != null
            }

            rankList = query.list { order("rank") }
            rankCount = rankList.countBy { it.rank.name }

            for (int d = 0; d < results.ranks.size(); d++) {
                if (rankCount.containsKey(results.ranks[d].toString())) {
                    results.yearRanks[d] += rankCount.get(results.ranks[d])
                    results.monthRanks[d][abs(m - 12)] += (rankCount.get(results.ranks[d]))
                }

            }
        }
        //Push "Other" to end of list

        def otherIndex = results.ranks.indexOf("Other (please indicate)")

        def tempRank = results.ranks.remove(otherIndex)
        def tempTotalRank = results.totalRanks.remove(otherIndex)
        def tempYearRank = results.yearRanks.remove(otherIndex)
        def tempMonthRank = results.monthRanks.remove(otherIndex)
        results.ranks.add(tempRank)
        results.totalRanks.add(tempTotalRank)
        results.yearRanks.add(tempYearRank)
        results.monthRanks.add(tempMonthRank)
        otherIndex = results.ranks.indexOf("Other (Please indicate)")

/*

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
        testCount = cnlist.countBy { it.courseName }
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
        results.libraryUnit = llist.countBy { it.ridLibraryUnit }*/

        return results
    }

    def statGraph(Map params, String transType) {
        def results = new RidStatisticsGraphReport()

        def query = RidConsTransaction.where {
            id >= 0
        }
        try {
            Date begin = new Date().minus(30);
            Date end = new Date()
            query = query.where {
                dateOfConsultation >= begin && dateOfConsultation < end.next()
            }
        } catch (Exception e) {}
        /*
        if (params.dateOfConsultation_start && params.dateOfConsultation_end) {
            try {
                Date start = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_start)
                Date end = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_end)
                query = query.where {
                    dateOfConsultation >= start && dateOfConsultation < end.next()
                }
            } catch (Exception e) {}
        }
        */
        def qList = query.list()
        results.transByDate = qList.countBy { it.dateOfConsultation }

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
