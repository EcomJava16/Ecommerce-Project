import { useRouter } from "next/router";

import LayoutOne from "../../components/layouts/LayoutOne";
import { capitalizeFirstLetter } from "../../common/utils";
import { getProductsBySlug } from "../../common/shopUtils";
import ProductDetailOne from "../../components/productDetail/ProductDetailOne";

export default function pid() {
  const router = useRouter();
  const productState = useSelector((state) => state.productReducer);
  const { slug } = router.query;
  const foundProduct = getProductsBySlug(productState.products, slug);
  return (
    <LayoutOne
      title={foundProduct && capitalizeFirstLetter(foundProduct.name)}
      clearSpaceTop
    >
      {foundProduct && <ProductDetailOne data={foundProduct} />}
    </LayoutOne>
  );
}
