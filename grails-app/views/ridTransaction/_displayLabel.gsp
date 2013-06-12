<li class="nav nav-pills">
<g:link controller="${linkController}" rel="tooltip" title="Switch to ${linkAction} Mode"
        action="${linkAction}"><i class="${icon}"></i> ${linkText}</g:link>
</li class="nav nav-pills">

<script type="text/javascript">
    $(function () {
        $("[rel='tooltip']").tooltip();
    });
</script>