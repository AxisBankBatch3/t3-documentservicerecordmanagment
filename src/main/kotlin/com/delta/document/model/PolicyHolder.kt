package com.delta.document.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*



@Document(collection = "PolicyHolder")
class PolicyHolder {
    @Id
    lateinit var id: String

    lateinit var partnerId:String

    lateinit var clientName : String

    lateinit var clientAddress : String

    lateinit var pinCode : String

    lateinit var emailId : String

    lateinit var mobile : String

    lateinit var aadharNumber: String

    lateinit var aadharDoc: String

    lateinit var panCardNumber: String

    lateinit var panCardDoc : String

    lateinit var insuranceType:String

    lateinit var policyNumber : String

    lateinit var policyQuote: String

    lateinit var relatedDocuments :String

    lateinit var submissionDate: Date

    constructor()
    constructor(
        partnerId: String,
        clientName: String,
        clientAddress: String,
        pinCode: String,
        emailId: String,
        mobile: String,
        aadharNumber: String?,
        aadharDoc: String?,
        panCardNumber: String?,
        panCardDoc: String?,
        insuranceType: String,
        policyNumber: String,
        policyQuote: String?,
        relatedDocuments: String?,
        submissionDate: Date
    ) {
        this.partnerId = partnerId
        this.clientName = clientName
        this.clientAddress = clientAddress
        this.pinCode = pinCode
        this.emailId = emailId
        this.mobile = mobile
        this.aadharNumber = aadharNumber!!
        this.aadharDoc = aadharDoc!!
        this.panCardNumber = panCardNumber!!
        this.panCardDoc = panCardDoc!!
        this.insuranceType = insuranceType
        this.policyNumber = policyNumber
        this.policyQuote = policyQuote!!
        this.relatedDocuments = relatedDocuments!!
        this.submissionDate = submissionDate
    }




}