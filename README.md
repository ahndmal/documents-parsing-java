# documents-parsing

Using Java APIs to create/edit and process documents (Word, Excel, Txt ...)

* Apache POI
* Apache TIka
* Jackson FasterXML
* JSON (Gson,Jackson,json-simple)


### POI

For basic (PDF complex) related stuff.

### Tika

![img.png](img.png)

```zsh
java -jar tika-app-1.0.jar < document.doc > extracted-text.xhtml

cat document.doc | java -jar tika-app-1.0.jar | grep some-text
```

from web:

```bash
java -jar tika-app-1.0.jar http://www.example.com/document.doc
```