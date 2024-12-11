//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package ClassGenerate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.</p>
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="orderperson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="shipto">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="item" maxOccurs="unbounded">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="quantity" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *                   <element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="orderid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderperson",
    "shipto",
    "item"
})

public class Shiporder {

    @XmlElement(required = true)
    protected String orderperson;
    @XmlElement(required = true)
    protected Shiporder.Shipto shipto;
    @XmlElement(required = true)
    protected List<Shiporder.Item> item;
    @XmlAttribute(name = "orderid", required = true)
    protected String orderid;

    /**
     * Obtiene el valor de la propiedad orderperson.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderperson() {
        return orderperson;
    }

    /**
     * Define el valor de la propiedad orderperson.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderperson(String value) {
        this.orderperson = value;
    }

    /**
     * Obtiene el valor de la propiedad shipto.
     * 
     * @return
     *     possible object is
     *     {@link Shiporder.Shipto }
     *     
     */
    public Shiporder.Shipto getShipto() {
        return shipto;
    }

    /**
     * Define el valor de la propiedad shipto.
     * 
     * @param value
     *     allowed object is
     *     {@link Shiporder.Shipto }
     *     
     */
    public void setShipto(Shiporder.Shipto value) {
        this.shipto = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Shiporder.Item }
     * </p>
     * 
     * 
     * @return
     *     The value of the item property.
     */
    public List<Shiporder.Item> getItem() {
        if (item == null) {
            item = new ArrayList<>();
        }
        return this.item;
    }

    /**
     * Obtiene el valor de la propiedad orderid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * Define el valor de la propiedad orderid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderid(String value) {
        this.orderid = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.</p>
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="quantity" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
     *         <element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "title",
        "note",
        "quantity",
        "price"
    })
    public static class Item {

        @XmlElement(required = true)
        protected String title;
        protected String note;
        @XmlElement(required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected int quantity;
        @XmlElement(required = true)
        protected double price;

        /**
         * Obtiene el valor de la propiedad title.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitle() {
            return title;
        }

        /**
         * Define el valor de la propiedad title.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Obtiene el valor de la propiedad note.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNote() {
            return note;
        }

        /**
         * Define el valor de la propiedad note.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNote(String value) {
            this.note = value;
        }

        /**
         * Obtiene el valor de la propiedad quantity.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public int getQuantity() {
            return quantity;
        }

        /**
         * Define el valor de la propiedad quantity.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setQuantity(int value) {
            this.quantity = value;
        }

        /**
         * Obtiene el valor de la propiedad price.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public double getPrice() {
            return price;
        }

        /**
         * Define el valor de la propiedad price.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPrice(double value) {
            this.price = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.</p>
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "address",
        "city",
        "country"
    })
    public static class Shipto {

        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String address;
        @XmlElement(required = true)
        protected String city;
        @XmlElement(required = true)
        protected String country;

        /**
         * Obtiene el valor de la propiedad name.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Define el valor de la propiedad name.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Obtiene el valor de la propiedad address.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAddress() {
            return address;
        }

        /**
         * Define el valor de la propiedad address.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddress(String value) {
            this.address = value;
        }

        /**
         * Obtiene el valor de la propiedad city.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCity() {
            return city;
        }

        /**
         * Define el valor de la propiedad city.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCity(String value) {
            this.city = value;
        }

        /**
         * Obtiene el valor de la propiedad country.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountry() {
            return country;
        }

        /**
         * Define el valor de la propiedad country.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountry(String value) {
            this.country = value;
        }

    }

}
