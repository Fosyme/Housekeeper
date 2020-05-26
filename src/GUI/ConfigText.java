package GUI;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;

public class ConfigText {
    private static final String config;
    private File file;

    static {
        config = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE config [\n" +
                "        <!ELEMENT config (account,options,setting)>\n" +
                "        <!ELEMENT account (user_name,user_pwd,user_show)>\n" +
                "        <!ELEMENT user_name     (#PCDATA)>\n" +
                "        <!ELEMENT user_pwd      (#PCDATA)>\n" +
                "        <!ELEMENT user_show     (#PCDATA)>\n" +
                "        <!ELEMENT options (remember_pwd,auto_login)>\n" +
                "        <!ELEMENT remember_pwd  (#PCDATA)>\n" +
                "        <!ELEMENT auto_login    (#PCDATA)>\n" +
                "        <!ELEMENT setting (language)>\n" +
                "        <!ELEMENT language      (#PCDATA)>\n" +
                "        ]>\n" +
                "<config>\n" +
                "    <account>\n" +
                "        <user_name>\n" +
                "            default_user_name\n" +
                "        </user_name>\n" +
                "        <user_pwd>\n" +
                "            default_user_pwd\n" +
                "        </user_pwd>\n" +
                "        <user_show>\n" +
                "            true\n" +
                "        </user_show>\n" +
                "    </account>\n" +
                "    <options>\n" +
                "        <remember_pwd>\n" +
                "            false\n" +
                "        </remember_pwd>\n" +
                "        <auto_login>\n" +
                "            false\n" +
                "        </auto_login>\n" +
                "    </options>\n" +
                "    <setting>\n" +
                "        <language>\n" +
                "\n" +
                "        </language>\n" +
                "    </setting>\n" +
                "</config>";
    }

    public ConfigText(File file) {
        this.file = file;
    }

    public boolean newConfig() {
        try {
            if (file.createNewFile()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(config.getBytes());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void readConfig() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList nl = doc.getElementsByTagName("account");
            for (int i = 0; i < nl.getLength(); i++) {
                System.out.println(doc.getElementsByTagName("user_name").item(i).getFirstChild().getNodeValue());
                System.out.println(doc.getElementsByTagName("user_pwd").item(i).getFirstChild().getNodeValue());
                System.out.println(doc.getElementsByTagName("user_show").item(i).getFirstChild().getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
