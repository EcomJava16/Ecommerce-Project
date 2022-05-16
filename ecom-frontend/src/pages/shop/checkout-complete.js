import { Form, Input, Button, Checkbox, Row, Col, Select } from "antd";
import classNames from "classnames";
import Slider from "react-slick";
import { useRouter } from "next/router";
import { useSelector } from "react-redux";
import Link from "next/link";

import LayoutOne from "../../components/layouts/LayoutOne";
import Container from "../../components/other/Container";
import { formatCurrency } from "../../common/utils";

export default function checkoutComplete({categories }) {
  return (
    <LayoutOne title="Checkout completed" shopData={categories.content}>
      <Container>
        <div className="checkout-complete">
          <div className="checkout-complete-summary">
            <h3>Congratulation! You’ve completed payment.</h3>
            <div className="checkout-complete-summary__table">
              <div className="checkout-complete-summary__table-item">
                <h5>Order Number</h5>
                <p>120</p>
              </div>
              <div className="checkout-complete-summary__table-item">
                <h5>Date</h5>
                <p>12 August 2020</p>
              </div>
              <div className="checkout-complete-summary__table-item">
                <h5>Total</h5>
                <p>{formatCurrency(200)}</p>
              </div>
              <div className="checkout-complete-summary__table-item">
                <h5>Payment methods</h5>
                <p>Check payment</p>
              </div>
            </div>
          </div>
          <div className="checkout-complete-details">
            <h3>Order Details</h3>
            <table>
              <thead>
                <tr>
                  <th>Product</th>
                  <th>Price</th>
                </tr>
              </thead>

              <tbody>
                <tr>
                  <td>Stay-Matte Sheer Pressed x 1</td>
                  <td className="bold">{formatCurrency(100)}</td>
                </tr>
                <tr>
                  <td>Subtotal</td>
                  <td className="bold">{formatCurrency(100)}</td>
                </tr>
                <tr>
                  <td>Shipping</td>
                  <td>Free ship</td>
                </tr>
                <tr>
                  <td>Payment Method</td>
                  <td>Check Payments</td>
                </tr>
                <tr>
                  <td>Total</td>
                  <td className="bold">{formatCurrency(100)}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </Container>
    </LayoutOne>
  );
}
export async function getServerSideProps() {
  console.log("fetch in checkout complete is called");
  // Fetch data from external API
  const [productRes, categoryRes] = await Promise.all([
    fetch("http://localhost:8080/api/v1/product"),
    fetch("http://localhost:8080/api/v1/category")
  ]);
  const [products, categories] = await Promise.all([
    productRes.json(),
    categoryRes.json()
  ]);
  return { props: {
    products,
    categories} 
    }
}
