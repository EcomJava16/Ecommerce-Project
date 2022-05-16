import { useRouter } from "next/router";

import LayoutOne from "../../components/layouts/LayoutOne";
import { capitalizeFirstLetter } from "../../common/utils";
import { getProductsBySlug } from "../../common/shopUtils";
import ProductDetailOne from "../../components/productDetail/ProductDetailOne";

export default function pid({products,categories}) {
  const router = useRouter();
  const { slug } = router.query;
  const foundProduct = getProductsBySlug(products.content, slug);
  return (
    <LayoutOne
      title={foundProduct && capitalizeFirstLetter(foundProduct.name)}
      clearSpaceTop
      shopData={categories.content}
    >
      {foundProduct && <ProductDetailOne data={foundProduct} />}
    </LayoutOne>
  );
}

export async function getServerSideProps() {
  console.log("fetch in [slug].js is called!")
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