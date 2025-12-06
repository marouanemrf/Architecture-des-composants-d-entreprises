package ma.projet.soapclient.ws

import ma.projet.soapclient.beans.Compte
import ma.projet.soapclient.beans.TypeCompte
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.text.SimpleDateFormat
import java.util.*

class Service {
    private val NAMESPACE = "http://ws.soapAcount/"
    private val URL = "http://10.0.2.2:8082/services/ws"
    private val METHOD_GET_COMPTES = "getComptes"
    private val METHOD_CREATE_COMPTE = "createCompte"
    private val METHOD_DELETE_COMPTE = "deleteCompte"

    // Helper to safely read properties from a SoapObject by name
    private fun SoapObject.getPropertySafe(name: String): String {
        return try {
            val prop = this.getProperty(name)
            prop?.toString() ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    fun getComptes(): List<Compte> {
        val request = SoapObject(NAMESPACE, METHOD_GET_COMPTES)
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11).apply {
            dotNet = false
            setOutputSoapObject(request)
        }
        val transport = HttpTransportSE(URL)
        val comptes = mutableListOf<Compte>()

        try {
            transport.call("", envelope)
            val body = envelope.bodyIn
            if (body is SoapObject) {
                val response = body
                for (i in 0 until response.propertyCount) {
                    val soapCompte = response.getProperty(i) as? SoapObject ?: continue
                    val idStr = soapCompte.getPropertySafe("id")
                    val soldeStr = soapCompte.getPropertySafe("solde")
                    val dateStr = soapCompte.getPropertySafe("dateCreation")
                    val typeStr = soapCompte.getPropertySafe("type")

                    val compte = Compte(
                        id = idStr.toLongOrNull(),
                        solde = soldeStr.toDoubleOrNull() ?: 0.0,
                        dateCreation = try {
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStr)
                                ?: Date()
                        } catch (e: Exception) {
                            Date()
                        },
                        type = try {
                            TypeCompte.valueOf(typeStr)
                        } catch (e: Exception) {
                            TypeCompte.COURANT
                        }
                    )
                    comptes.add(compte)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return comptes
    }

    fun createCompte(solde: Double, type: TypeCompte): Boolean {
        val request = SoapObject(NAMESPACE, METHOD_CREATE_COMPTE).apply {
            addProperty("solde", solde)
            addProperty("type", type.name)
        }
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11).apply {
            dotNet = false
            setOutputSoapObject(request)
        }
        val transport = HttpTransportSE(URL)

        return try {
            transport.call("", envelope)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun deleteCompte(id: Long): Boolean {
        val request = SoapObject(NAMESPACE, METHOD_DELETE_COMPTE).apply {
            addProperty("id", id)
        }
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11).apply {
            dotNet = false
            setOutputSoapObject(request)
        }
        val transport = HttpTransportSE(URL)

        return try {
            transport.call("", envelope)
            val resp = envelope.response
            if (resp is Boolean) resp else resp.toString().toBoolean()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
