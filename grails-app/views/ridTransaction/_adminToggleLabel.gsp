<li class="nav nav-pills blue">
    <g:link controller="${linkController}" rel="tooltip" title="Switch to Administrative Tasks"
            action="${linkAction}"><i class="${icon}"></i> ${linkText}</g:link>
</li>

<script type="text/javascript">
    $(function () {
        $("[rel='tooltip']").tooltip();
    });
</script>