<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="informe">
        <html>
            <head>
                <title>Informe a apoderados</title>
            </head>
            <body>
                <xsl:apply-templates select="apoderado"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="apoderado">
        <p>
            <xsl:value-of select="apoderado"/>
        </p>
        <xsl:for-each select="alumno">
            <p>
                <xsl:value-of select="@nombre"/>
            </p>
            <xsl:for-each select="actividad">
                <p>
                    <xsl:value-of select="@nombre"/>
                </p>
                <p>
                    <xsl:text>Ramo: </xsl:text>
                    <xsl:value-of select="ramo"/>
                </p>
                <p>
                    <xsl:text>Tipo: </xsl:text>
                    <xsl:value-of select="tipo"/>
                </p>
                <p>
                    <xsl:text>Descripción: </xsl:text>
                    <xsl:value-of select="descripcion"/>
                </p>
                <p>
                    <xsl:text>Fecha: </xsl:text>
                    <xsl:value-of select="fechaA"/>
                </p>
                <p>
                    <xsl:text>Evaluado: </xsl:text>
                    <xsl:value-of select="evaluado"/>
                </p>
            </xsl:for-each>
            <p>
                <xsl:text>Notas: </xsl:text>
                <xsl:for-each select="nota">
                    <xsl:value-of select="."/>
                    <xsl:text>; </xsl:text>
                </xsl:for-each>
            </p>
            <p>
                <xsl:text>Promedio: </xsl:text>
                <xsl:value-of select="promedio"/>
                <xsl:text>; Aprueba: </xsl:text>
                <xsl:value-of select="promedio/@aprueba"/>
            </p>
            <p>
                <xsl:text>Anotaciones</xsl:text>
            </p>
            <xsl:for-each select="anotacion">
                <p>
                    <xsl:value-of select="."/>
                </p>
            </xsl:for-each>
            <p>
                <xsl:text>Asistencia</xsl:text>
            </p>
            <xsl:for-each select="asistencia/fecha">
                <p>
                    <xsl:value-of select="."/>
                    <xsl:text>: </xsl:text>
                    <xsl:value-of select="@presente"/>
                </p>
            </xsl:for-each>
            <p>
                <xsl:text>Porcentaje :</xsl:text>
                <xsl:value-of select="asistencia/porcentaje"/>
            </p>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>