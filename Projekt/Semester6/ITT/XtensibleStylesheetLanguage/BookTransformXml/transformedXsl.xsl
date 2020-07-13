<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" doctype-system="book2.dtd"/>
    <xsl:template match="/">
        <Books>
            <xsl:call-template name="autor"/>
            <xsl:call-template name="titel"/>
            <xsl:for-each select="/Book/chapter">
                <chapter>
                    <xsl:attribute name="id">
                        <xsl:value-of select="@id"/>
                    </xsl:attribute>
                    <title>
                        <xsl:value-of select="concat(count(preceding-sibling::chapter)+1,'. ', title)"/>
                    </title>
                    <Inhalt>
                        <xsl:value-of select="text" />
                    </Inhalt>
                </chapter>
            </xsl:for-each>
            <toc>
                <xsl:for-each select="/Book/toc/entry">
                <xsl:copy-of select="."/>
                </xsl:for-each>
            </toc>
        </Books>
    </xsl:template>
    <xsl:template name="autor" match="/Book/Autor">
        <Autor>
            <Vorname>
                <xsl:choose>
                    <xsl:when test="contains(/Book/Autor, '. ')">
                        <xsl:value-of select="substring-before(/Book/Autor, '. ')" />
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="substring-before(/Book/Autor, ' ')" />
                    </xsl:otherwise>
                </xsl:choose>
            </Vorname>
            <Nachname>
                <xsl:choose>
                    <xsl:when test="contains(/Book/Autor, '. ')">
                        <xsl:value-of select="substring-after(/Book/Autor, '. ')" />
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="substring-after(/Book/Autor, ' ')" />
                    </xsl:otherwise>
                </xsl:choose>
            </Nachname>
        </Autor>
    </xsl:template>
    <xsl:template name="titel" match="/Book/Autor">
        <titel>
            <xsl:value-of select="/Book/title" />
        </titel>
    </xsl:template>
</xsl:stylesheet>