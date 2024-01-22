package com.project.System.app;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Component
class Warmup implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		/*
		Set<UserEntity> users = new HashSet<UserEntity>();
		ObjectMapper objMapper = new ObjectMapper();
		
		for (int i = 1; i <= 65001; i++)
		{
			users.add(new UserEntity("name"+i, "surname"+i, "login"+i));
		}
		
		try {
			objMapper.writeValue(new FileOutputStream("usersList.json"), users);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			 
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
 
            Element root = document.createElement("users");
            document.appendChild(root);
            
            for (int i = 1; i <= 50001; i++)
            {
            	Element user = document.createElement("user");
                root.appendChild(user);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode("name" + i));
                user.appendChild(name);
     
                Element surname = document.createElement("surname");
                surname.appendChild(document.createTextNode("surname" + i));
                user.appendChild(surname);
     
                Element login = document.createElement("login");
                login.appendChild(document.createTextNode("login" + i));
                user.appendChild(login);
            }
         
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("userXML.xml"));
 
            transformer.transform(domSource, streamResult);
 
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        */
    }
}

