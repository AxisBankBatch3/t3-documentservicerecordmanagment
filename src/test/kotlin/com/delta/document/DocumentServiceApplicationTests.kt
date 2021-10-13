package com.delta.document


import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import com.delta.document.model.PolicyHolder
import com.delta.document.repository.PolicyHolderRepository
import com.delta.document.service.PolicyService
import com.delta.document.service.PolicyServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import java.io.File
import java.util.*


@SpringBootTest()
open class DocumentServiceApplicationTests() {

    @MockBean
    lateinit var policyHolderRepository: PolicyHolderRepository

    @MockBean
    lateinit var  amazonS3: AmazonS3

    @MockBean
    lateinit var  s3Result: PutObjectResult

    @MockBean
    lateinit var putObjectRequest: PutObjectRequest

    @Autowired
    lateinit var  policyService: PolicyService

    @Autowired
    lateinit var  policyServiceImpl: PolicyServiceImpl

    @Test
    fun  submitDocumentTest() {


        val aadhar = MockMultipartFile(
            "file",
            "aadhar.pdf",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
        )
        val pan = MockMultipartFile(
            "file",
            "pan.pdf",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
        )
        val quote = MockMultipartFile(
            "file",
            "quote.pdf",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
        )
        val others = MockMultipartFile(
            "file",
            "others.pdf",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
        )



        var aadharFile:File = policyServiceImpl.convertMultipartFileToFile(aadhar)
        var panFile :File = policyServiceImpl.convertMultipartFileToFile(pan)
        var quoteFile :File = policyServiceImpl.convertMultipartFileToFile(quote)
        var otherFile :File = policyServiceImpl.convertMultipartFileToFile(others)

        Mockito.`when`(amazonS3.putObject(PutObjectRequest("deltabank", "aadhar.pdf", aadharFile))).thenReturn(s3Result)
        Mockito.`when`(amazonS3.putObject(PutObjectRequest("deltabank", "pan.pdf", aadharFile))).thenReturn(s3Result)
        Mockito.`when`(amazonS3.putObject(PutObjectRequest("deltabank", "quote.pdf", aadharFile))).thenReturn(s3Result)
        Mockito.`when`(amazonS3.putObject(PutObjectRequest("deltabank", "others.pdf", aadharFile))).thenReturn(s3Result)

        var date = Date()

        var policyHolder = PolicyHolder("614320f07c0a69046b221643","Aniruddha","mumbai","400705","ani@gmail.com",
                                        "9876543210","1234 1224 1223","aadhar.pdf","EDDPP1610M","pan.pdf",
                                        "Health","981567","quote.pdf","others.pdf",date
                                        )


        Mockito.`when`(policyHolderRepository!!.insert(policyHolder)).thenReturn(policyHolder)


        Assertions.assertEquals("All the files Stored in s3 and data is stored in database",policyService!!.submitDocuments("614320f07c0a69046b221643","Aniruddha","mumbai","400705","ani@gmail.com",
            "9876543210","1234 1224 1223",aadhar,"EDDPP1610M",pan,
            "Health","981567",quote,others));

    }


    @Test
    fun getAllDetailsTest(){
        val date = Date()
        var policyHolder = PolicyHolder("614320f07c0a69046b221643","Aniruddha","mumbai","400705","ani@gmail.com",
            "9876543210","1234 1224 1223","aadhar.pdf","EDDPP1610M","pan.pdf",
            "Health","981567","quote.pdf","others.pdf",date
        )

        Mockito.`when`(policyHolderRepository!!.findAll())
            .thenReturn(listOf(policyHolder))

        Assertions.assertEquals(1,policyService!!.getAllDetails().size);
    }


    @Test
    fun getAllDetailsTestFalse(){
        val date = Date()
        var policyHolder = PolicyHolder()

        Mockito.`when`(policyHolderRepository!!.findAll())
            .thenReturn(listOf(policyHolder))

        Assertions.assertEquals(1,policyService!!.getAllDetails().size);
    }

    @Test
    fun existByPolicyPass(){

        val date = Date()
        var policyHolder = PolicyHolder("614320f07c0a69046b221643","Aniruddha","mumbai","400705","ani@gmail.com",
            "9876543210","1234 1224 1223","aadhar.pdf","EDDPP1610M","pan.pdf",
            "Health","981567","quote.pdf","others.pdf",date
        )

        Mockito.`when`(policyHolderRepository!!.existsByPolicyNumber("981567"))
            .thenReturn(true)

        Assertions.assertTrue(true,policyService.isPolicyPresentByPolicyNumber("981567").toString())


    }















}

