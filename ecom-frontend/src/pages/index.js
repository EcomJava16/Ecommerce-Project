import { useSelector } from "react-redux";
import { useRouter } from "next/router";

import Banners from "../components/shop/Banners";
import LayoutOne from "../components/layouts/LayoutOne";
import ShopLayout from "../components/shop/ShopLayout";
import useProductData from "../common/useProductData";

export default function Home({products,categories}) {
  const router = useRouter();
  const globalState = useSelector((state) => state.globalReducer);
  const data = useProductData(
    products.content,
    globalState.category,
    router.query.q
  );
  return (
    <LayoutOne title="Homepage 1" shopData={categories.content}>
      <Banners />
      <ShopLayout
        fiveColumn
        shopSidebarResponsive={{ xs: 24, lg: 4 }}
        shopContentResponsive={{ xs: 24, lg: 20 }}
        productResponsive={{ xs: 12, sm: 8, md: 6 }}
        productPerPage={15}
        data={[...data]}
        categoryData={categories.content}
      />
    </LayoutOne>
  );
}

export async function getServerSideProps() {
  console.log("fetch in index.js is called!")
  // Fetch data from external API
  const [productRes, categoryRes] = await Promise.all([
    fetch("http://localhost:8080/api/v1/product"),
    fetch("http://localhost:8080/api/v1/category")
  ]);
  const [products, categories] = await Promise.all([
    productRes.json(),
    categoryRes.json()
  ]);
  console.log("fetch!");
  return { props: {
    products,
    categories} 
    }
}
