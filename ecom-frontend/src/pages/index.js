import { useSelector } from "react-redux";
import { useRouter } from "next/router";
import { useDispatch } from "react-redux";

import Banners from "../components/shop/Banners";
import LayoutOne from "../components/layouts/LayoutOne";
import ShopLayout from "../components/shop/ShopLayout";
import useProductData from "../common/useProductData";
import { useEffect, useState } from "react";
import fetchProducts from "../redux/actions/fetchProducts";
import fetchCategory from "../redux/actions/fetchCategory";

export default function Home({products,categories}) {
  const router = useRouter();
  const dispatch = useDispatch();
  const productState = useSelector((state) => state.productReducer);
  useEffect(() => {
    dispatch(fetchProducts());
  }, []);
  const categoryState = useSelector((state) => state.categoryReducer);
  useEffect(() => {
    dispatch(fetchCategory());
  }, []);
  const globalState = useSelector((state) => state.globalReducer);
  const data = useProductData(
    productState.products,
    globalState.category,
    router.query.q
  );
  return (
    <LayoutOne title="Homepage 1">
      <Banners />
      <ShopLayout
        fiveColumn
        shopSidebarResponsive={{ xs: 24, lg: 4 }}
        shopContentResponsive={{ xs: 24, lg: 20 }}
        productResponsive={{ xs: 12, sm: 8, md: 6 }}
        productPerPage={15}
        data={[...data]}
      />
    </LayoutOne>
  );
}
