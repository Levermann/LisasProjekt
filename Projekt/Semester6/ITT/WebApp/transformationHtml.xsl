<!---worksheet 3 Exercise 1: XSLT script  to transform an XML lectureDescription.xml to HTML-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="autor" match="Autor">
    </xsl:template>

    <xsl:template name="title" match="/Book/title">
        <title>
            <xsl:value-of select="/Book/title"/>
        </title>
    </xsl:template>

</xsl:stylesheet>