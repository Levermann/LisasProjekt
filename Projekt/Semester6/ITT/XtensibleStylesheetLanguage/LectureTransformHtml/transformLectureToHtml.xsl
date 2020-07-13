<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" doctype-system="lectureDescriptionValidation.dtd"/>
    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" type="text/css" href="lecture.css" media="all"/>
                <title>Blatt 3 Aufgabe 1</title>
            </head>
            <body>
                <h1>Vorlesung:</h1>
                <h3><xsl:value-of select="concat('Veranstaltungsname: ', Vorlesung/Titel)"/></h3>
                <h3><xsl:value-of select="concat('Semester: ', Vorlesung/Semester)"/></h3>
                <h3><xsl:value-of select="concat('Hochschule: ', Vorlesung/Hochschule)"/></h3>
                <h3><xsl:value-of select="concat('Semesterwochenstunden: ', Vorlesung/SWS)"/></h3>
                <xsl:call-template name="Kapitel"/>
                <xsl:call-template name="Quelle"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template name="Kapitel" match="/Vorlesung/Kapitel">
        <Kapitel>
            <xsl:for-each select="/Vorlesung/Kapitel">
                <xsl:attribute name="quelle">
                    <xsl:value-of select="@quelle"/>
                </xsl:attribute>
                <dl><xsl:value-of select="Name"/></dl>
                <dt><xsl:value-of select="Termin"/></dt>
            </xsl:for-each>
        </Kapitel>
    </xsl:template>
    <xsl:template name="Quelle" match="/Vorlesung/Quelle">
        <Quelle>
            <xsl:for-each select="/Vorlesung/Quelle">
                <xsl:attribute name="quellenID">
                    <xsl:value-of select="@quellenID"/>
                </xsl:attribute>
                <dd><xsl:value-of select="Autor"/></dd>
                <dd><xsl:value-of select="Titel"/></dd>
            </xsl:for-each>
        </Quelle>
    </xsl:template>
</xsl:stylesheet>


