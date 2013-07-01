$(document).ready(function () {

    $('#Table1 td:nth-child(5), #Table1  th:nth-child(5)').hide();
    $('#Table1 td:nth-child(6), #Table1  th:nth-child(6)').hide();
    $('#Table1   td:nth-child(7), #Table1  th:nth-child(7)').hide();
    $('#Table1   td:nth-child(8), #Table1  th:nth-child(8)').hide();
    $('#Table1   td:nth-child(9), #Table1  th:nth-child(9)').hide();
    $('#Table1   td:nth-child(10), #Table1  th:nth-child(10)').hide();
    $('#Table1   td:nth-child(11), #Table1  th:nth-child(11)').hide();
    $('#Table1   td:nth-child(12), #Table1  th:nth-child(12)').hide();
    $('#Table1   td:nth-child(13), #Table1  th:nth-child(13)').hide();
    $('#Table1   td:nth-child(14), #Table1  th:nth-child(14)').hide();
    $('#Table1   td:nth-child(15), #Table1  th:nth-child(15)').hide();
    $('#Table1   td:nth-child(16), #Table1  th:nth-child(16)').hide();
    $('#Table1   td:nth-child(17), #Table1  th:nth-child(17)').hide();

    $('#Table2 td:nth-child(4), #Table2  th:nth-child(4)').hide();
    $('#Table2 td:nth-child(5), #Table2  th:nth-child(5)').hide();
    $('#Table2 td:nth-child(6), #Table2  th:nth-child(6)').hide();
    $('#Table2   td:nth-child(7), #Table2  th:nth-child(7)').hide();
    $('#Table2   td:nth-child(8), #Table2  th:nth-child(8)').hide();
    $('#Table2   td:nth-child(9), #Table2  th:nth-child(9)').hide();
    $('#Table2   td:nth-child(10), #Table2  th:nth-child(10)').hide();
    $('#Table2   td:nth-child(11), #Table2  th:nth-child(11)').hide();
    $('#Table2   td:nth-child(12), #Table2  th:nth-child(12)').hide();
    $('#Table2   td:nth-child(13), #Table2  th:nth-child(13)').hide();
    $('#Table2   td:nth-child(14), #Table2  th:nth-child(14)').hide();
    $('#Table2   td:nth-child(15), #Table2  th:nth-child(15)').hide();
    $('#Table2   td:nth-child(16), #Table2  th:nth-child(16)').hide();

    $('#Table3 td:nth-child(4), #Table3  th:nth-child(4)').hide();
    $('#Table3 td:nth-child(5), #Table3  th:nth-child(5)').hide();
    $('#Table3 td:nth-child(6), #Table3  th:nth-child(6)').hide();
    $('#Table3   td:nth-child(7), #Table3  th:nth-child(7)').hide();
    $('#Table3   td:nth-child(8), #Table3  th:nth-child(8)').hide();
    $('#Table3   td:nth-child(9), #Table3  th:nth-child(9)').hide();
    $('#Table3   td:nth-child(10), #Table3  th:nth-child(10)').hide();
    $('#Table3   td:nth-child(11), #Table3  th:nth-child(11)').hide();
    $('#Table3   td:nth-child(12), #Table3  th:nth-child(12)').hide();
    $('#Table3   td:nth-child(13), #Table3  th:nth-child(13)').hide();
    $('#Table3   td:nth-child(14), #Table3  th:nth-child(14)').hide();
    $('#Table3   td:nth-child(15), #Table3  th:nth-child(15)').hide();
    $('#Table3   td:nth-child(16), #Table3  th:nth-child(16)').hide();

    $('#Table4 td:nth-child(4), #Table4  th:nth-child(4)').hide();
    $('#Table4 td:nth-child(5), #Table4  th:nth-child(5)').hide();
    $('#Table4 td:nth-child(6), #Table4  th:nth-child(6)').hide();
    $('#Table4   td:nth-child(7), #Table4  th:nth-child(7)').hide();
    $('#Table4   td:nth-child(8), #Table4  th:nth-child(8)').hide();
    $('#Table4   td:nth-child(9), #Table4  th:nth-child(9)').hide();
    $('#Table4   td:nth-child(10), #Table4  th:nth-child(10)').hide();
    $('#Table4   td:nth-child(11), #Table4  th:nth-child(11)').hide();
    $('#Table4   td:nth-child(12), #Table4  th:nth-child(12)').hide();
    $('#Table4   td:nth-child(13), #Table4  th:nth-child(13)').hide();
    $('#Table4   td:nth-child(14), #Table4  th:nth-child(14)').hide();
    $('#Table4   td:nth-child(15), #Table4  th:nth-child(15)').hide();
    $('#Table4   td:nth-child(16), #Table4  th:nth-child(16)').hide();


    $('#Table2 tr').slice(6).hide();
    $('#Table3 tr').slice(6).hide();


    $('#toggleCol').click(function () {


        $('#Table1 td:nth-child(2), #Table1 th:nth-child(2)').toggle();
        $('#Table1 td:nth-child(3), #Table1 th:nth-child(3)').toggle();
        $('#Table1 td:nth-child(4), #Table1 th:nth-child(4)').toggle();
        $('#Table1 td:nth-child(5), #Table1 th:nth-child(5)').toggle();
        $('#Table1 td:nth-child(6), #Table1 th:nth-child(6)').toggle();
        $('#Table1 td:nth-child(7), #Table1 th:nth-child(7)').toggle();
        $('#Table1 td:nth-child(8), #Table1 th:nth-child(8)').toggle();
        $('#Table1 td:nth-child(9), #Table1 th:nth-child(9)').toggle();
        $('#Table1 td:nth-child(10), #Table1 th:nth-child(10)').toggle();
        $('#Table1 td:nth-child(11), #Table1 th:nth-child(11)').toggle();
        $('#Table1 td:nth-child(12), #Table1 th:nth-child(12)').toggle();
        $('#Table1 td:nth-child(13), #Table1 th:nth-child(13)').toggle();
        $('#Table1 td:nth-child(14), #Table1 th:nth-child(14)').toggle();
        $('#Table1 td:nth-child(15), #Table1 th:nth-child(15)').toggle();
        $('#Table1 td:nth-child(16), #Table1 th:nth-child(16)').toggle();
        $('#Table1 td:nth-child(17), #Table1 th:nth-child(17)').toggle();
        if (document.getElementById("toggleCol").value == "Expand to monthly view") {
            document.getElementById("toggleCol").value = "Return to overview";
        }
        else {
            document.getElementById("toggleCol").value = "Expand to monthly view";
        }


        // if your table has header(th), use this
        //$('td:nth-child(2),th:nth-child(2)').hide();

    });
    $('#toggleCol2').click(function () {


        $('#Table2 td:nth-child(2), #Table2 th:nth-child(2)').toggle();
        $('#Table2 td:nth-child(3), #Table2 th:nth-child(3)').toggle();
        $('#Table2 td:nth-child(4), #Table2 th:nth-child(4)').toggle();
        $('#Table2 td:nth-child(5), #Table2 th:nth-child(5)').toggle();
        $('#Table2 td:nth-child(6), #Table2 th:nth-child(6)').toggle();
        $('#Table2 td:nth-child(7), #Table2 th:nth-child(7)').toggle();
        $('#Table2 td:nth-child(8), #Table2 th:nth-child(8)').toggle();
        $('#Table2 td:nth-child(9), #Table2 th:nth-child(9)').toggle();
        $('#Table2 td:nth-child(10), #Table2 th:nth-child(10)').toggle();
        $('#Table2 td:nth-child(11), #Table2 th:nth-child(11)').toggle();
        $('#Table2 td:nth-child(12), #Table2 th:nth-child(12)').toggle();
        $('#Table2 td:nth-child(13), #Table2 th:nth-child(13)').toggle();
        $('#Table2 td:nth-child(14), #Table2 th:nth-child(14)').toggle();
        $('#Table2 td:nth-child(15), #Table2 th:nth-child(15)').toggle();
        $('#Table2 td:nth-child(16), #Table2 th:nth-child(16)').toggle();

        if (document.getElementById("toggleCol2").value == "Expand to monthly view") {
            document.getElementById("toggleCol2").value = "Return to overview";

        }
        else {
            document.getElementById("toggleCol2").value = "Expand to monthly view";

        }


        // if your table has header(th), use this
        //$('td:nth-child(2),th:nth-child(2)').hide();

    });

    $('#toggleCol3').click(function () {


        $('#Table3 td:nth-child(2), #Table3 th:nth-child(2)').toggle();
        $('#Table3 td:nth-child(3), #Table3 th:nth-child(3)').toggle();
        $('#Table3 td:nth-child(4), #Table3 th:nth-child(4)').toggle();
        $('#Table3 td:nth-child(5), #Table3 th:nth-child(5)').toggle();
        $('#Table3 td:nth-child(6), #Table3 th:nth-child(6)').toggle();
        $('#Table3 td:nth-child(7), #Table3 th:nth-child(7)').toggle();
        $('#Table3 td:nth-child(8), #Table3 th:nth-child(8)').toggle();
        $('#Table3 td:nth-child(9), #Table3 th:nth-child(9)').toggle();
        $('#Table3 td:nth-child(10), #Table3 th:nth-child(10)').toggle();
        $('#Table3 td:nth-child(11), #Table3 th:nth-child(11)').toggle();
        $('#Table3 td:nth-child(12), #Table3 th:nth-child(12)').toggle();
        $('#Table3 td:nth-child(13), #Table3 th:nth-child(13)').toggle();
        $('#Table3 td:nth-child(14), #Table3 th:nth-child(14)').toggle();
        $('#Table3 td:nth-child(15), #Table3 th:nth-child(15)').toggle();
        $('#Table3 td:nth-child(16), #Table3 th:nth-child(16)').toggle();

        if (document.getElementById("toggleCol3").value == "Expand to monthly view") {
            document.getElementById("toggleCol3").value = "Return to overview";

        }
        else {
            document.getElementById("toggleCol3").value = "Expand to monthly view";

        }


        // if your table has header(th), use this
        //$('td:nth-child(2),th:nth-child(2)').hide();

    });

    $('#toggleCol4').click(function () {


        $('#Table4 td:nth-child(2), #Table4 th:nth-child(2)').toggle();
        $('#Table4 td:nth-child(3), #Table4 th:nth-child(3)').toggle();
        $('#Table4 td:nth-child(4), #Table4 th:nth-child(4)').toggle();
        $('#Table4 td:nth-child(5), #Table4 th:nth-child(5)').toggle();
        $('#Table4 td:nth-child(6), #Table4 th:nth-child(6)').toggle();
        $('#Table4 td:nth-child(7), #Table4 th:nth-child(7)').toggle();
        $('#Table4 td:nth-child(8), #Table4 th:nth-child(8)').toggle();
        $('#Table4 td:nth-child(9), #Table4 th:nth-child(9)').toggle();
        $('#Table4 td:nth-child(10), #Table4 th:nth-child(10)').toggle();
        $('#Table4 td:nth-child(11), #Table4 th:nth-child(11)').toggle();
        $('#Table4 td:nth-child(12), #Table4 th:nth-child(12)').toggle();
        $('#Table4 td:nth-child(13), #Table4 th:nth-child(13)').toggle();
        $('#Table4 td:nth-child(14), #Table4 th:nth-child(14)').toggle();
        $('#Table4 td:nth-child(15), #Table4 th:nth-child(15)').toggle();
        $('#Table4 td:nth-child(16), #Table4 th:nth-child(16)').toggle();

        if (document.getElementById("toggleCol4").value == "Expand to monthly view") {
            document.getElementById("toggleCol4").value = "Return to overview";

        }
        else {
            document.getElementById("toggleCol4").value = "Expand to monthly view";

        }


        // if your table has header(th), use this
        //$('td:nth-child(2),th:nth-child(2)').hide();

    });


    $('#allDepts').click(function () {

        $('#Table2 tr').slice(1).toggle();
        if (document.getElementById("allDepts").value == "Show all Departments") {
            document.getElementById("allDepts").value = "Show top five Departments";

        }
        else {
            document.getElementById("allDepts").value = "Show all Departments";

        }

    });

    $('#allCourses').click(function () {

        $('#Table3 tr').slice(1).toggle();
        if (document.getElementById("allCourses").value == "Show all Courses") {
            document.getElementById("allCourses").value = "Show top five Courses";

        }
        else {
            document.getElementById("allCourses").value = "Show all Courses";

        }

    });

})

