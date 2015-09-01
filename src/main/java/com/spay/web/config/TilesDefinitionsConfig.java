package com.spay.web.config;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

import java.util.HashMap;
import java.util.Map;

public class TilesDefinitionsConfig implements DefinitionsFactory {

	private static final Map<String, Definition> tilesDefinitions = new HashMap<String,Definition>();
	private static final String BASE_TEMPLATE = "/WEB-INF/views/tiles/layout/";
	
	@Override
	public Definition getDefinition(String name, Request tilesContext) {
		return tilesDefinitions.get(name);
	}

	private static void addDefaultLayoutDef(String name, String title, String body) {
		Map<String, Attribute> attributes = new HashMap<String,Attribute>();
		attributes.put("title", new Attribute(title));
		attributes.put("header", new Attribute("/WEB-INF/views/tiles/layout/header.jsp"));
		attributes.put("menu", new Attribute("/WEB-INF/views/tiles/layout/menu.jsp"));
		attributes.put("body", new Attribute(body));
		attributes.put("footer", new Attribute("/WEB-INF/views/tiles/layout/footer.jsp"));
		tilesDefinitions.put(name, new Definition(name, new Attribute(BASE_TEMPLATE + "defaultLayout.jsp"), attributes));
	}

	public static void addDefinitions() {
		addDefaultLayoutDef("tiles", "test", "/WEB-INF/views/tiles/tiles.jsp");
		addDefaultLayoutDef("tiles/*", "test", "/WEB-INF/views/tiles/{1}.jsp");
	}
}
