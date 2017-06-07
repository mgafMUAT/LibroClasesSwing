<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="urn:schemas-microsoft-com:office:spreadsheet"
                xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
                xmlns:x="urn:schemas-microsoft-com:office:excel">
    <xsl:template match="informe">
        <Workbook>
            <Styles>
                <Style ss:ID="Default" ss:Name="Normal">
                    <Alignment ss:Vertical="Bottom" />
                    <Borders />
                    <Font />
                    <Interior />
                    <NumberFormat />
                    <Protection />
                </Style>
                <Style ss:ID="s21">
                    <Font ss:Size="22" ss:Bold="1" />
                </Style>
                <Style ss:ID="s22">
                    <Font ss:Size="14" ss:Bold="1" />
                </Style>
                <Style ss:ID="s23">
                    <Font ss:Size="12" ss:Bold="1" />
                </Style>
                <Style ss:ID="s24">
                    <Font ss:Size="10" ss:Bold="1" />
                </Style>
            </Styles>
            <Worksheet ss:Name="Informe">
                <Table>
                    <xsl:apply-templates select="apoderado"/>
                </Table>
            </Worksheet>
        </Workbook>
    </xsl:template>
    <xsl:template match="apoderado">
        <Row>
            <Cell>
                <Data ss:Type="String">Apoderado</Data>
            </Cell>
            <Cell>
                <Data ss:Type="String">
                    <xsl:value-of select="@nombre"/>
                </Data>
            </Cell>
        </Row>
        <xsl:for-each select="alumno">
            <Row>
                <Cell>
                    <Data ss:Type="String">Pupilo</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">
                        <xsl:value-of select="@nombre"/>
                    </Data>
                </Cell>
            </Row>
            <Row>
                <Cell>
                    <Data ss:Type="String">Planificación</Data>
                </Cell>
            </Row>
            <Row>
                <Cell>
                    <Data ss:Type="String">Actividad</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">Ramo</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">Tipo</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">Fecha</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">Evaluado</Data>
                </Cell>
            </Row>
            <xsl:for-each select="actividad">
                <Row>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="@nombre"/>
                        </Data>
                    </Cell>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="ramo"/>
                        </Data>
                    </Cell>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="tipo"/>
                        </Data>
                    </Cell>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="fechaA"/>
                        </Data>
                    </Cell>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="evaluado"/>
                        </Data>
                    </Cell>
                </Row>
            </xsl:for-each>
            <Row>
                <Cell>
                    <Data ss:Type="String">Notas</Data>
                </Cell>
                <xsl:for-each select="nota">
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="."/>
                        </Data>
                    </Cell>
                </xsl:for-each>
                <Cell>
                    <Data ss:Type="String">Promedio</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">
                        <xsl:value-of select="promedio"/>
                    </Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">Aprueba</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">
                        <xsl:value-of select="@aprueba"/>
                    </Data>
                </Cell>
            </Row>
            <Row>
                <Cell>
                    <Data ss:Type="String">Anotaciones</Data>
                </Cell>
            </Row>
            <xsl:for-each select="anotacion">
                <Row>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="anotacion"/>
                        </Data>
                    </Cell>
                </Row>
            </xsl:for-each>
            <Row>
                <Cell>
                    <Data ss:Type="String">Asistencia</Data>
                </Cell>
            </Row>
            <xsl:for-each select="asistencia/fecha">
                <Row>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="."/>
                        </Data>
                    </Cell>
                    <Cell>
                        <Data ss:Type="String">
                            <xsl:value-of select="@presente"/>
                        </Data>
                    </Cell>
                </Row>
            </xsl:for-each>
            <Row>
                <Cell>
                    <Data ss:Type="String">Porcentaje</Data>
                </Cell>
                <Cell>
                    <Data ss:Type="String">
                        <xsl:value-of select="asistencia/porcentaje"/>
                    </Data>
                </Cell>
            </Row>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>