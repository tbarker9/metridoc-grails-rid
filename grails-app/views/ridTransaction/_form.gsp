<%@ page import="metridoc.rid.RidDepartment;metridoc.rid.RidSchool;metridoc.rid.RidLocation;metridoc.rid.RidRank;java.text.SimpleDateFormat;metridoc.rid.RidUserGoal;metridoc.rid.RidLibraryUnit;metridoc.rid.RidLibraryUnit;metridoc.rid.RidDepartment;metridoc.rid.RidCourseSponsor;metridoc.rid.RidConsTransaction;metridoc.rid.RidInsTransaction" %>

<r:external dir="datepicker/css" file="datepicker.css" plugin="metridoc-rid"/>
<r:external dir="datepicker/js" file="bootstrap-datepicker.js" plugin="metridoc-rid"/>
<r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>
<r:external dir="js" file="RidTransaction.js" plugin="metridoc-rid"/>

<g:if test="${session.transType == "consultation"}">
    <tmpl:formCons/>
</g:if>
<g:else>
    <tmpl:formIns/>
</g:else>