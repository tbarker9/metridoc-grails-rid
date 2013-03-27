//$(function() {
//    $("#foo").change(function() {
//            var strSel = $("#foo").val().join(",");
//            $("#p1").html(strSel);
//    })
//})

$(function() {
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

    var dateOfConsultation = $('#dp1').datepicker({
//        onRender: function(date) {
//            return date.valueOf() < now.valueOf() ? 'disabled' : '';
//        }
    }).on('changeDate', function(ev) {
            dateOfConsultation.hide();
            $('#dp1').blur();
        }).data('datepicker');


    $('#dpd1').datepicker('setValue', now);
    $('#dpd2').datepicker('setValue', now);
    var checkin = $('#dpd1').datepicker().on('changeDate', function(ev) {
            if (ev.date.valueOf() > checkout.date.valueOf()) {
                var newDate = new Date(ev.date)
                newDate.setDate(newDate.getDate());
                checkout.setValue(newDate);
            }
            checkin.hide();
            $('#dpd2')[0].focus();
        }).data('datepicker');
    var checkout = $('#dpd2').datepicker()
        .on('changeDate', function(ev) {
            if (ev.date.valueOf() < checkin.date.valueOf()) {
                var newDate = new Date(ev.date)
                newDate.setDate(newDate.getDate());
                checkin.setValue(newDate);
            }
            checkout.hide();
        }).data('datepicker');

});

$(function() {
    var choiceUser = $("#user option:selected").attr("inForm");
    if (choiceUser == "2") {
        $("#otherUserDiv").show();
        $("#otherUser").val("");
    }
    var choiceService = $("#serviceProvided option:selected").attr("inForm");
    if (choiceService == "2") {
        $("#otherServiceDiv").show();
        $("#otherService").val("");
    }
    var choiceUserAffiliation = $("#userAffiliation option:selected").attr("inForm");
    if (choiceUserAffiliation == "2") {
        $("#otherUserAffiliationDiv").show();
        $("#otherUserAffiliation").val("");
    }
    var choiceCourseSponsor = $("#courseSponsor option:selected").attr("inForm");
    if (choiceCourseSponsor == "2") {
        $("#otherCourseSponsorDiv").show();
        $("#otherCourseSponsor").val("");
    }
    var choiceUserGoal = $("#userGoal option:selected").attr("inForm");
    if (choiceUserGoal == "2") {
        $("#otherUserGoalDiv").show();
        $("#otherUserGoal").val("");
    }
    var choiceMode = $("#modeOfConsultation option:selected").attr("inForm");
    if (choiceMode == "2") {
        $("#otherModeOfConsultationDiv").show();
        $("#otherModeOfConsultation").val("");
    }
});

$(function() {
    $("#modeOfConsultation").change(function() {
        var choice = $("#modeOfConsultation option:selected").attr("inForm");
        //alert(choice)
        if (choice == "2") {
            $("#otherModeOfConsultationDiv").show();
        }
        else {
            $("#otherModeOfConsultationDiv").hide();
            $("#otherModeOfConsultation").val("");
        }
    })
});

$(function() {
    $("#userGoal").change(function() {
        var choice = $("#userGoal option:selected").attr("inForm");
        //alert(choice)
        if (choice == "2") {
            $("#otherUserGoalDiv").show();
        }
        else {
            $("#otherUserGoalDiv").hide();
            $("#otherUserGoal").val("");
        }
    })
});

$(function() {
    $("#user").change(function() {
        var choice = $("#user option:selected").attr("inForm");
        //alert(choice)
        if (choice == "2") {
            $("#otherUserDiv").show();
        }
        else {
            $("#otherUserDiv").hide();
            $("#otherUser").val("");
        }
    })
});

$(function() {
    $("#serviceProvided").change(function() {
        var choice = $("#serviceProvided option:selected").attr("inForm");
        if (choice == "2") {
            $("#otherServiceDiv").show();
        }
        else {
            $("#otherServiceDiv").hide();
            $("#otherService").val("");
        }
    })
});

$(function() {
    $("#userAffiliation").change(function() {
        var choice = $("#userAffiliation option:selected").attr("inForm");
        if (choice == "2") {
            $("#otherUserAffiliationDiv").show();
        }
        else {
            $("#otherUserAffiliationDiv").hide();
            $("#otherUserAffiliation").val("");
        }
    })
});

$(function() {
    $("#courseSponsor").change(function() {
        var choice = $("#courseSponsor option:selected").attr("inForm");
        if (choice == "2") {
            $("#otherCourseSponsorDiv").show();
        }
        else {
            $("#otherCourseSponsorDiv").hide();
            $("#otherCourseSponsor").val("");
        }
    })
});

$(function() {
    $("#resetButton").click(function() {
        $("#otherUserDiv").hide();
        $("#otherUser").val("");
        $("#otherServiceDiv").hide();
        $("#otherService").val("");
        $("#otherUserAffiliationDiv").hide();
        $("#otherUserAffiliation").val("");
        $("#otherCourseSponsorDiv").hide();
        $("#otherCourseSponsor").val("");
        $("#otherUserGoalDiv").hide();
        $("#otherUserGoal").val("");
    })
});

$(function() {
    $("#ridReportType").change(function() {
        var choiceType = $("#ridReportType").val();
        var choiceMode = $("#currentModeOfConsultation").text();
        var choiceService = $("#currentServiceProvided").text();
        var choiceGoal = $("#currentUserGoal").text();
        $.ajax({
            //url: '${g.createLink(controller: 'RidTransaction', action: 'ajaxChooseType')}',
            url: 'ajaxChooseType',
            type: 'POST',
            dataType: 'json',
            data: {
                typeId: choiceType,
                modeID: choiceMode,
                serviceID: choiceService,
                goalID: choiceGoal
            },
            success: function(data) {
                $.each(data, function(index, itemList) {
                    $('#'+index+' > option').remove();
                    $.each(itemList, function(id, element) {
                        $('#'+index).append($("<option>", {
                            text: element.name
                        }).attr('value', element.id).attr('inForm', element.inForm));
                    });
                    if(itemList.length==0)
                        $('#'+index).attr("disabled","");
                    else
                        $('#'+index).removeAttr("disabled");
                });
            },
            error: function(request, status, error) {
                alert(error);
            },
            complete: function() {
                var choiceService = $("#serviceProvided > option:first").attr("inForm");
                if (choiceService == "2") {
                    $("#otherServiceDiv").show();
                }
                else {
                    $("#otherServiceDiv").hide();
                    $("#otherService").val("");
                }
                var choiceUserGoal = $("#userGoal > option:first").attr("inForm");
                if (choiceUserGoal == "2") {
                    $("#otherUserGoalDiv").show();
                }
                else {
                    $("#otherUserGoalDiv").hide();
                    $("#otherUserGoal").val("");
                }
                var choiceMode = $("#modeOfConsultation > option:first").attr("inForm");
                if (choiceMode == "2") {
                    $("#otherModeOfConsultationDiv").show();
                }
                else {
                    $("#otherModeOfConsultationDiv").hide();
                    $("#otherModeOfConsultation").val("");
                }
            }
        });

    })
});

function removeRequired() {
    $("#ridReportType").removeAttr("required");
    $("#dateOfConsultation").removeAttr("required");
    $("#staffPennkey").removeAttr("required");
    $("#modeOfConsultation").removeAttr("required");
    $("#serviceProvided").removeAttr("required");
    $("#prepTime").removeAttr("required");
    $("#eventLength").removeAttr("required");
    $("#user").removeAttr("required");
    $("#userAffiliation").removeAttr("required");
    $("#interactTimes").removeAttr("required");
    $("#departmentalAffilication").removeAttr("required");
    $("#courseSponsor").removeAttr("required");
    $("#userQuestion").removeAttr("required");
}

function setRequired() {
    $("#ridReportType").attr("required", "");
    $("#dateOfConsultation").attr("required", "");
    $("#staffPennkey").attr("required", "");
    $("#modeOfConsultation").attr("required", "");
    $("#serviceProvided").attr("required", "");
    $("#prepTime").attr("required", "");
    $("#eventLength").attr("required", "");
    $("#user").attr("required", "");
    $("#userAffiliation").attr("required", "");
    $("#interactTimes").attr("required", "");
    $("#departmentalAffilication").attr("required", "");
    $("#courseSponsor").attr("required", "");
    $("#userQuestion").attr("required", "");
}