
package wcf.omcorderwrapperservice.org.datacontract.schemas._2004._07.omcsystem_services_servicecontract;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OrderProductSection complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrderProductSection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OriginalPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="ProductCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ProductDetailID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ProductGroupID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ProductID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="ProductInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProductNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProductPicture" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProductPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="ProductSourceTypeID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ProductTotalPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderProductSection", propOrder = {
    "originalPrice",
    "productCount",
    "productDetailID",
    "productGroupID",
    "productID",
    "productInfo",
    "productName",
    "productNumber",
    "productPicture",
    "productPrice",
    "productSourceTypeID",
    "productTotalPrice"
})
public class OrderProductSection {

    @XmlElement(name = "OriginalPrice")
    protected BigDecimal originalPrice;
    @XmlElement(name = "ProductCount")
    protected Integer productCount;
    @XmlElement(name = "ProductDetailID")
    protected Integer productDetailID;
    @XmlElement(name = "ProductGroupID")
    protected Integer productGroupID;
    @XmlElement(name = "ProductID")
    protected Long productID;
    @XmlElementRef(name = "ProductInfo", namespace = "http://schemas.datacontract.org/2004/07/OMCSystem.Services.ServiceContract.Model", type = JAXBElement.class)
    protected JAXBElement<String> productInfo;
    @XmlElementRef(name = "ProductName", namespace = "http://schemas.datacontract.org/2004/07/OMCSystem.Services.ServiceContract.Model", type = JAXBElement.class)
    protected JAXBElement<String> productName;
    @XmlElementRef(name = "ProductNumber", namespace = "http://schemas.datacontract.org/2004/07/OMCSystem.Services.ServiceContract.Model", type = JAXBElement.class)
    protected JAXBElement<String> productNumber;
    @XmlElementRef(name = "ProductPicture", namespace = "http://schemas.datacontract.org/2004/07/OMCSystem.Services.ServiceContract.Model", type = JAXBElement.class)
    protected JAXBElement<String> productPicture;
    @XmlElement(name = "ProductPrice")
    protected BigDecimal productPrice;
    @XmlElement(name = "ProductSourceTypeID")
    protected Integer productSourceTypeID;
    @XmlElement(name = "ProductTotalPrice")
    protected BigDecimal productTotalPrice;

    /**
     * 获取originalPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 设置originalPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOriginalPrice(BigDecimal value) {
        this.originalPrice = value;
    }

    /**
     * 获取productCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProductCount() {
        return productCount;
    }

    /**
     * 设置productCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProductCount(Integer value) {
        this.productCount = value;
    }

    /**
     * 获取productDetailID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProductDetailID() {
        return productDetailID;
    }

    /**
     * 设置productDetailID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProductDetailID(Integer value) {
        this.productDetailID = value;
    }

    /**
     * 获取productGroupID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProductGroupID() {
        return productGroupID;
    }

    /**
     * 设置productGroupID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProductGroupID(Integer value) {
        this.productGroupID = value;
    }

    /**
     * 获取productID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductID() {
        return productID;
    }

    /**
     * 设置productID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductID(Long value) {
        this.productID = value;
    }

    /**
     * 获取productInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProductInfo() {
        return productInfo;
    }

    /**
     * 设置productInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProductInfo(JAXBElement<String> value) {
        this.productInfo = value;
    }

    /**
     * 获取productName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProductName() {
        return productName;
    }

    /**
     * 设置productName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProductName(JAXBElement<String> value) {
        this.productName = value;
    }

    /**
     * 获取productNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProductNumber() {
        return productNumber;
    }

    /**
     * 设置productNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProductNumber(JAXBElement<String> value) {
        this.productNumber = value;
    }

    /**
     * 获取productPicture属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProductPicture() {
        return productPicture;
    }

    /**
     * 设置productPicture属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProductPicture(JAXBElement<String> value) {
        this.productPicture = value;
    }

    /**
     * 获取productPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 设置productPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProductPrice(BigDecimal value) {
        this.productPrice = value;
    }

    /**
     * 获取productSourceTypeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProductSourceTypeID() {
        return productSourceTypeID;
    }

    /**
     * 设置productSourceTypeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProductSourceTypeID(Integer value) {
        this.productSourceTypeID = value;
    }

    /**
     * 获取productTotalPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    /**
     * 设置productTotalPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProductTotalPrice(BigDecimal value) {
        this.productTotalPrice = value;
    }

}
