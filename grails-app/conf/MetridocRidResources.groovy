modules = {

    tableModule {
        dependsOn "application"
        resource id: 'css',
                url: [dir: 'css', file: 'pagination.css'],
                attrs: [type: 'css']
        resource id: 'css',
                url: [dir: 'css', file: 'table.css'],
                attrs: [type: 'css']
        resource id: 'css',
                url: [dir: 'css', file: 'floating_table.css'],
                attrs: [type: 'css']
    }
    datePicker {
        resource id: 'css',
                url: [dir: 'datepicker/css', file: 'datepicker.css'],
                attrs: [type: 'css']
        resource id: 'js',
                url: [dir: 'datepicker/js', file: 'bootstrap-datepicker.js'],
                attrs: [type: 'js']
    }

    ridTransaction {
        dependsOn "application"
        resource id: 'css',
                url: [dir: 'css', file: 'RidTransaction.css'],
                attrs: [type: 'css']
        resource id: 'js',
                url: [dir: 'js', file: 'RidTransaction.js'],
                attrs: [type: 'js']
    }

    statistics {
        resource id: 'js',
                url: [dir: 'js', file: 'RidStatistics.js'],
                attrs: [type: 'js']
        resource id: 'js',
                url: [dir: 'js', file: 'RidStatisticsGraph.js'],
                attrs: [type: 'js']
    }


}