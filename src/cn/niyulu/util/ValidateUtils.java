package cn.niyulu.util;

public class ValidateUtils {
    /**
     * 验证数据是否为空
     * @param data 要执行验证的数据
     * @return 数据为空返回false,
     */
    public static boolean validateEmpty(String data){
        if (data == null || "".equals(data)){
            return true;
        }
        return false;
    }

	public static boolean validateEmpty(Integer id) {
		 if (id == null){
	            return true;
	     }
	     return false;
	}
}
