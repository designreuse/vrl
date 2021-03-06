package vrl.lang;

//
//package eu.mihosoft.vrl.lang;
//
//import groovy.lang.GroovyClassLoader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Map;
//import org.codehaus.groovy.ast.ClassNode;
//import org.codehaus.groovy.ast.ModuleNode;
//import org.codehaus.groovy.classgen.GeneratorContext;
//import org.codehaus.groovy.classgen.VariableScopeVisitor;
//import org.codehaus.groovy.control.CompilationFailedException;
//import org.codehaus.groovy.control.CompilerConfiguration;
//import org.codehaus.groovy.control.Phases;
//import org.codehaus.groovy.control.SourceUnit;
//import org.codehaus.groovy.tools.javac.JavaAwareResolveVisitor;
//import org.codehaus.groovy.tools.javac.JavaCompiler;
//import org.codehaus.groovy.tools.javac.JavaCompilerFactory;
//import org.codehaus.groovy.tools.javac.JavaStubGenerator;
//import org.codehaus.groovy.tools.javac.JavacCompilerFactory;
//
///**
// *
// * @author Michael Hoffer <info@michaelhoffer.de>
// */
///**
// * Created by IntelliJ IDEA. 
// * User: Alex.Tkachman 
// * Date: May 31, 2007 Time: 6:48:28 PM 
// */
//public class JavaAwareCompilationUnit extends CompilationUnit {
//    private LinkedList javaSources; // java sources
//    private JavaStubGenerator stubGenerator;
//    private JavaCompilerFactory compilerFactory = new JavacCompilerFactory();
//    private File generationGoal;
//    private boolean keepStubs;
//    
//    public JavaAwareCompilationUnit(CompilerConfiguration configuration) {
//        this(configuration,null);
//    }
//    
//    public JavaAwareCompilationUnit(CompilerConfiguration configuration, GroovyClassLoader groovyClassLoader) {
//        super(configuration,null,groovyClassLoader);
//        javaSources = new LinkedList();
//        Map options = configuration.getJointCompilationOptions();
//        generationGoal = (File) options.get("stubDir");
//        boolean useJava5 = configuration.getTargetBytecode().equals(CompilerConfiguration.POST_JDK5);
//        stubGenerator = new JavaStubGenerator(generationGoal,false,useJava5);
//        keepStubs = Boolean.TRUE.equals(options.get("keepStubs"));
//        
//        addPhaseOperation(new PrimaryClassNodeOperation() {
//            public void call(SourceUnit source, GeneratorContext context, ClassNode node) throws CompilationFailedException {
//                if (javaSources.size() != 0) {
//                	VariableScopeVisitor scopeVisitor = new VariableScopeVisitor(source);
//                	scopeVisitor.visitClass(node);
//                	new JavaAwareResolveVisitor(JavaAwareCompilationUnit.this).startResolving(node,source);
//                }
//            }
//        },Phases.CONVERSION);
//
//        addPhaseOperation(new PrimaryClassNodeOperation() {
//            public void call(SourceUnit source, GeneratorContext context, ClassNode classNode) throws CompilationFailedException {
//                try {
//                    if (javaSources.size() != 0) stubGenerator.generateClass(classNode);
//                } catch (FileNotFoundException fnfe) {
//                    source.addException(fnfe);
//                }
//            }
//        },Phases.CONVERSION);
//    }
//
//    public void gotoPhase(int phase) throws CompilationFailedException {
//        super.gotoPhase(phase);
//        // compile Java and clean up
//        if (phase==Phases.SEMANTIC_ANALYSIS && javaSources.size()>0) {
//            Iterator modules = getAST().getModules().iterator();
//            while (modules.hasNext()) {
//                ModuleNode module = (ModuleNode) modules.next();
//                module.setImportsResolved(false);
//            }
//            try {
//                JavaCompiler compiler = compilerFactory.createCompiler(getConfiguration());
//                compiler.compile(javaSources, this);
//            } finally {
//                if (!keepStubs) stubGenerator.clean(); 
//                javaSources.clear();
//            }
//        }
//    }
//    
//    public void configure(CompilerConfiguration configuration) {
//        super.configure(configuration);
//        // GroovyClassLoader should be able to find classes compiled from java
//        // sources
//        File targetDir = configuration.getTargetDirectory();
//        if (targetDir != null) {
//            final String classOutput = targetDir.getAbsolutePath();
//            getClassLoader().addClasspath(classOutput);
//        }
//    }
//
//    private void addJavaSource(File file) {
//        String path = file.getAbsolutePath();
//        for (Iterator iter = javaSources.iterator(); iter.hasNext();) {
//            String su = (String) iter.next();
//            if (path.equals(su))
//                return;
//        }
//        javaSources.add(path);
//    }
//
//    public void addSources(String[] paths) {
//        for (int i = 0; i < paths.length; i++) {
//            File file = new File(paths[i]);
//            if (file.getName().endsWith(".java"))
//                addJavaSource(file);
//            else
//                addSource(file);
//        }
//    }
//
//    public void addSources(File[] files) {
//        for (int i = 0; i < files.length; i++) {
//            if (files[i].getName().endsWith(".java"))
//                addJavaSource(files[i]);
//            else
//                addSource(files[i]);
//        }
//    }
//
//    public JavaCompilerFactory getCompilerFactory() {
//        return compilerFactory;
//    }
//
//    public void setCompilerFactory(JavaCompilerFactory compilerFactory) {
//        this.compilerFactory = compilerFactory;
//    }
//}
//
