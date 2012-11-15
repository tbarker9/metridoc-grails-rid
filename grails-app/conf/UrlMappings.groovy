class UrlMappings {

	static mappings = {
        "/"(controller: "home", action: "index")
        "/admin"(view: "/admin/index")
        "/data/$action?/$projectName?/$filePath**?"(controller: "data")

        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "500"(view: '/error')
	}
}
