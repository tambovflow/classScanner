package a;

import java.lang.reflect.*;

public class ClassScan {

    public String scan(Class cl){
        StringBuilder sb = new StringBuilder();
        sb.append("\\\\---------------ClassScanner--by--Rafael--2018-----------------------\\\\\n\n");

        String modif = Modifier.toString(cl.getModifiers());
        String impl = " implements ";
        sb.append(modif + " ");
        if(!modif.contains("interface")){
        	sb.append("class " + cl.getSimpleName());
            if(!cl.getSimpleName().equals("Object"))
            sb.append(" extends " + cl.getSuperclass().getSimpleName());
        }else{
        	sb.append(cl.getSimpleName());
            impl = " extends ";
        }
        Class[] interfaces = cl.getInterfaces();
        if(interfaces.length>0){
            sb.append(impl);
            for(Class inf : interfaces){
                sb.append(inf.getSimpleName() + ", ");
            }
            sb.append("\b\b");
        }
        sb.append("{\n\n");
        scanFields(cl,sb);
        scanConstructors(cl, sb);
        scanMethods(cl, sb);
        sb.append("}");
        return sb.toString();
    }
    private void scanFields(Class cl,StringBuilder sb){
        sb.append("\\\\ Fields\n");
        Field[] fields = cl.getDeclaredFields();
        for(Field f  : fields){
            sb.append("\t");
            String modif = Modifier.toString(f.getModifiers());
            if(modif.length()>0){
                sb.append(modif + " ");
            }
            sb.append(f.getType().getSimpleName() + " " +f.getName() + ";\n");
        }
        sb.append("\n");
    }

    private void scanConstructors(Class cl, StringBuilder sb){
        sb.append("\\\\ Constructors\n");
        Constructor[] constructors = cl.getDeclaredConstructors();
        for(Constructor ct : constructors){
            sb.append("\t");
            String modif = Modifier.toString(ct.getModifiers());
            if(modif.length()>0){
                sb.append(modif + " ");
            }
            sb.append(cl.getSimpleName() +"(");
            Class[] paramTypes = ct.getParameterTypes();
            if(paramTypes.length>0){
                for(Class pt : paramTypes){
                    sb.append(pt.getSimpleName() + ", ");
                }
                sb.delete(sb.length() - 2, sb.length());
            }
            sb.append(")");
            Class[] exceptionsType = ct.getExceptionTypes();
            if(exceptionsType.length>0){
                sb.append(" throws ");
                for(Class et  : exceptionsType){
                    sb.append(et.getSimpleName() + ", ");
                }
                sb.delete(sb.length()-2, sb.length());
            }
            sb.append("{ ... } \n");
        }
        sb.append("\n");
    }

    private void scanMethods(Class cl, StringBuilder sb){
        sb.append("\\\\ Methods\n");
        Method[] methods = cl.getDeclaredMethods();
        for(Method m: methods){
            sb.append("\t");
            String modif = Modifier.toString(m.getModifiers());
            if(modif.length()>0){
                sb.append(modif + " ");
            }
            sb.append(m.getReturnType().getSimpleName() + " " + m.getName() + "(");
            Class[] paramTypes = m.getParameterTypes();
            if(paramTypes.length>0){
                for(Class pt : paramTypes){
                    sb.append(pt.getSimpleName() +", ");
                }
                sb.delete(sb.length()-2, sb.length());
            }
            sb.append(")");
            Class[] exceptionsType = m.getExceptionTypes();
            if(exceptionsType.length>0){
                sb.append(" throws ");
                for(Class et  : exceptionsType){
                    sb.append(et.getSimpleName() + ", ");
                }
                sb.delete(sb.length()-2, sb.length());
            }
            sb.append("{ ... }\n");
        }
        sb.append("\n");
    }

}
