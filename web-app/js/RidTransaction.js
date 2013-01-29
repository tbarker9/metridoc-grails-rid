//$(function() {
//    $("#foo").change(function() {
//            var strSel = $("#foo").val().join(",");
//            $("#p1").html(strSel);
//    })
//})


$(function() {
    var choiceCustomer = $("#customer").val();
    if (choiceCustomer == "9") {
        $("#otherCustomerDiv").show();
        $("#otherCustomer").val("");
    }
    var choiceService = $("#serviceProvided").val();
    if (choiceService == "21") {
        $("#otherServiceDiv").show();
        $("#otherService").val("");
    }
    var choiceEntityAffiliation = $("#entityAffiliation").val();
    if (choiceEntityAffiliation == "15" || choiceEntityAffiliation == "16") {
        $("#otherEntityAffiliationDiv").show();
        $("#otherEntityAffiliation").val("");
    }
    var choiceCourseSponsor = $("#courseSponsor").val();
    if (choiceCourseSponsor == "15") {
        $("#otherCourseSponsorDiv").show();
        $("#otherCourseSponsor").val("");
    }
})

$(function() {
    $("#customer").change(function() {
        var choice = $("#customer").val();
        //alert(choice)
        if (choice == "9") {
            $("#otherCustomerDiv").show();
        }
        else {
            $("#otherCustomerDiv").hide();
            $("#otherCustomer").val("");
        }
    })
})

$(function() {
    $("#serviceProvided").change(function() {
        var choice = $("#serviceProvided").val();
        if (choice == "21") {
            $("#otherServiceDiv").show();
        }
        else {
            $("#otherServiceDiv").hide();
            $("#otherService").val("");
        }
    })
})

$(function() {
    $("#entityAffiliation").change(function() {
        var choice = $("#entityAffiliation").val();
        if (choice == "15" || choice == "16") {
            $("#otherEntityAffiliationDiv").show();
        }
        else {
            $("#otherEntityAffiliationDiv").hide();
            $("#otherEntityAffiliation").val("");
        }
    })
})

$(function() {
    $("#courseSponsor").change(function() {
        var choice = $("#courseSponsor").val();
        if (choice == "15") {
            $("#otherCourseSponsorDiv").show();
        }
        else {
            $("#otherCourseSponsorDiv").hide();
            $("#otherCourseSponsor").val("");
        }
    })
})

$(function() {
    $("#resetButton").click(function() {
        $("#otherCustomerDiv").hide();
        $("#otherCustomer").val("");
        $("#otherServiceDiv").hide();
        $("#otherService").val("");
        $("#otherEntityAffiliationDiv").hide();
        $("#otherEntityAffiliation").val("");
        $("#otherCourseSponsorDiv").hide();
        $("#otherCourseSponsor").val("");
    })
})