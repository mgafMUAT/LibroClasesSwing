<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="informe">
        <html>
            <head>
                <title>Apoderados con más de un alumno</title>
            </head>
            <body>
            
            </body>
        </html>
    </xsl:template>
    <xsl:template match="apoderado">
        <xsl:for-each select="alumno">
            
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>