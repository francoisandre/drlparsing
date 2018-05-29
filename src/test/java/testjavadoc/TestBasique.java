package testjavadoc;

import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.drools.compiler.compiler.DrlParser;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.lang.descr.AnnotationDescr;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.compiler.lang.descr.RuleDescr;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.builder.conf.LanguageLevelOption;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class TestBasique {


	@Test
	public void test1() throws Exception{
		ClassLoader cl = Thread.currentThread().getContextClassLoader().getClass().getClassLoader();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
		Resource[] resources = resolver.getResources("classpath*:**/rules/**/*.drl");
		for (int i = 0; i < resources.length; i++) {
			String ruleContent = IOUtils.toString(resources[i].getInputStream(), StandardCharsets.UTF_8.name());
			DrlParser parser = new DrlParser();
			File file = new File("C:/Users/François ANDRE/workspaceSageFA/testjavadoc/src/main/resources/rules/test5.drl");
			ruleContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
			System.out.println(ruleContent);
			PackageDescr pkgDescr =null;
			try {
				pkgDescr = parser.parse( null, ruleContent );
			}
			catch (DroolsParserException e) {
				e.printStackTrace();
			}
			Assert.assertNotNull(pkgDescr);
			List<RuleDescr> rules = pkgDescr.getRules();
			RuleDescr ruleDescr = rules.get(0);
			AnnotationDescr annotation = ruleDescr.getAnnotation("Toto");
			
			String valueAsString = annotation.getValuesAsString();
			
			annotation = ruleDescr.getAnnotation("BugReport");
			
			
			String string = annotation.getValue("severity").toString();
			String string2 = annotation.getValue("DefaultMessage").toString();
			
			
			
			//			DrlParser parser = new DrlParser();
			//			PackageDescr descr = parser.parse(new InputStreamReader(resources[i].getInputStream()));

			System.out.println("ccc");
		}
	}

}
