package top.veritasal.genericitylearn.productgetter;

public class MainClass {
    public static void main(String[] args) {

        //添加奖品
        ProductGetter<String> stringProductGetter = new ProductGetter<>();
        String[] strProduct = {"苹果手机", "苹果电脑", "扫地机器人", "华为手机", "咖啡机", "特斯拉电动跑车", "邮轮旅行"};
        for (int i = 0; i < strProduct.length; i++) {
            stringProductGetter.addProduct(strProduct[i]);
        }

        //抽奖
        String product1 = stringProductGetter.getProduct();
        System.out.println("恭喜你中奖了，奖品是： " + product1);

        //创建整形对象
        ProductGetter<Integer> integerProductGetter = new ProductGetter<>();
        int[] intProduct = {1000, 20000, 300000, 5000, 80000, 90000};

        System.out.println("=======================================");
        //添加奖品
        for (int i = 0; i < intProduct.length; i++) {
            integerProductGetter.addProduct(intProduct[i]);
        }

        //抽奖
        Integer product2 = integerProductGetter.getProduct();
        System.out.println("恭喜你，中奖了，现金：" + product2 + "元");

    }

}
