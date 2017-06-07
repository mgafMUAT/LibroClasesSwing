<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" indent="yes"/>
    <xsl:template match="informe">
        <w:wordDocument
            xmlns:w="http://schemas.microsoft.com/office/word/2003/wordml">
            <w:body>
                <w:p>
                    <w:r>
                        <w:t>
                            <xsl:text>Informe a apoderados:&#xa;</xsl:text>
                        </w:t>
                    </w:r>
                </w:p>
                <w:p>
                    <w:r>
                        <w:t>
                            <xsl:apply-templates select="apoderado"/>
                        </w:t>
                    </w:r>
                </w:p>
            </w:body>
        </w:wordDocument>
    </xsl:template>
    <xsl:template match="apoderado">
        <xsl:text>Apoderado: </xsl:text>
        <xsl:value-of select="@nombre"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:for-each select="alumno">
            <xsl:text>Pupilo: </xsl:text>
            <xsl:value-of select="@nombre"/>
            <xsl:text>&#xa;Planificación:&#xa;</xsl:text>
            <xsl:for-each select="actividad">
                <xsl:text>Actividad: </xsl:text>
                <xsl:value-of select="@nombre"/>
                <xsl:text>&#xa;</xsl:text>
                <xsl:text>Ramo: </xsl:text>
                <xsl:value-of select="ramo"/>
                <xsl:text>&#xa;</xsl:text>
                <xsl:text>Tipo: </xsl:text>
                <xsl:value-of select="tipo"/>
                <xsl:text>&#xa;</xsl:text>
                <xsl:text>Descripción: </xsl:text>
                <xsl:value-of select="descripcion"/>
                <xsl:text>&#xa;</xsl:text>
                <xsl:text>Fecha: </xsl:text>
                <xsl:value-of select="fechaA"/>
                <xsl:text>&#xa;</xsl:text>
                <xsl:text>Evaluado: </xsl:text>
                <xsl:value-of select="evaluado"/>
                <xsl:text>&#xa;</xsl:text>
            </xsl:for-each>
            <xsl:text>Notas: </xsl:text>
            <xsl:for-each select="nota">
                <xsl:value-of select="."/>
                <xsl:text>; </xsl:text>
            </xsl:for-each>
            <xsl:text>&#xa;</xsl:text>
            <xsl:text>Promedio: </xsl:text>
            <xsl:value-of select="promedio"/>
            <xsl:text>; Aprueba: </xsl:text>
            <xsl:value-of select="@aprueba"/>
            <xsl:text>&#xa;Anotaciones&#xa;</xsl:text>
            <xsl:for-each select="anotacion">
                <xsl:value-of select="."/>
                <xsl:text>&#xa;</xsl:text>
            </xsl:for-each>
            <xsl:text>Asistencia:&#xa;</xsl:text>
            <xsl:for-each select="asistencia/fecha">
                <xsl:value-of select="."/>
                <xsl:text>: </xsl:text>
                <xsl:value-of select="@presente"/>
                <xsl:text>&#xa;</xsl:text>
            </xsl:for-each>
            <xsl:text>Porcentaje: </xsl:text>
            <xsl:value-of select="asistencia/porcentaje"/>
            <xsl:text>&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>