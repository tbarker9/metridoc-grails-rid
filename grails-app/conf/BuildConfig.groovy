grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

//location of the release repository
grails.project.repos.metridocInternalRepo.url = "svn:http://svn2.library.upenn.int/svn/dev/correctFormat/metridoc/plugins/maven/repository/"
//name of the repository
grails.project.repos.default = "metridocInternalRepo"

grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        mavenCentral()

        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://metridoc.googlecode.com/svn/maven/repository/"
    }
    dependencies {
        //get this to play nice with intellij, not actually needed
        build("org.tmatesoft.svnkit:svnkit:1.3.5") {
            excludes "jna", "trilead-ssh2", "sqljet"
        }
        build("com.google.code.maven-svn-wagon:maven-svn-wagon:1.4")
        //for excel file parser
        compile("org.apache.poi:poi:3.8-beta3")
        compile("org.apache.poi:poi-ooxml:3.8-beta3") {
            excludes 'poi'
            excludes 'dom4j'
        }

    }

    plugins {
        build(":rest-client-builder:1.0.3")
        //compile(":metridoc-core:0.53-SNAPSHOT")
        compile(":metridoc-core:0.54.4-SNAPSHOT")
        build ":release:$grailsVersion"
        build(":tomcat:$grailsVersion") {
            export = false
        }

    }
}
