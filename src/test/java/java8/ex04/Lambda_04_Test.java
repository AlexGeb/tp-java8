package java8.ex04;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Exercice 04 - FuncCollection Exercice synthèse des exercices précédents
 */
public class Lambda_04_Test {

	// tag::interfaces[]
	interface GenericPredicate<T> {
		// TODO
		boolean test(T t);
	}

	interface GenericMapper<T, E> {
		// TODO
		E mapper(T t);
	}

	interface Processor<T> {
		// TODO
		void process(T t);
	}
	// end::interfaces[]

	// tag::FuncCollection[]
	class FuncCollection<T> {

		private Collection<T> list = new ArrayList<>();

		public void add(T a) {
			list.add(a);
		}

		public void addAll(Collection<T> all) {
			for (T el : all) {
				list.add(el);
			}
		}
		// end::FuncCollection[]

		// tag::methods[]
		private FuncCollection<T> filter(GenericPredicate<T> predicate) {
			FuncCollection<T> result = new FuncCollection<>();
			// TODO
			for (T l : list) {
				if (predicate.test(l)) {
					result.add(l);
				}
			}
			return result;
		}

		private <E> FuncCollection<E> map(GenericMapper<T, E> mapper) {
			FuncCollection<E> result = new FuncCollection<>();
			// TODO
			for (T l : list) {
				result.add(mapper.mapper(l));
			}
			return result;
		}

		private void forEach(Processor<T> processor) {
			// TODO
			for (T l : list) {
				processor.process(l);
			}
		}
		// end::methods[]

	}

	// tag::test_filter_map_forEach[]
	@Test
	public void test_filter_map_forEach() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		FuncCollection<Person> personFuncCollection = new FuncCollection<Person>();
		personFuncCollection.addAll(personList);

		personFuncCollection
				// TODO filtrer, ne garder uniquement que les personnes ayant un age > 50
				.filter(p -> p.getAge() > 50)
				// TODO transformer la liste de personnes en liste de comptes. Un compte a par
				// défaut un solde à 1000.
				.map(p -> {
					Account acc = new Account();
					acc.setOwner(p);
					acc.setBalance(1000);
					return acc;
				})
				// TODO vérifier que chaque compte a un solde à 1000.
				// TODO vérifier que chaque titulaire de compte a un age > 50
				.forEach(acc -> {
					assert acc.getBalance().equals(1000);
					assert acc.getOwner().getAge() > 50;
				});

		// TODO à supprimer
		// assert false;
	}
	// end::test_filter_map_forEach[]

	// tag::test_filter_map_forEach_with_vars[]
	@Test
	public void test_filter_map_forEach_with_vars() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		FuncCollection<Person> personFuncCollection = new FuncCollection<>();
		personFuncCollection.addAll(personList);

		// TODO créer une variable filterByAge de type GenericPredicate
		// TODO filtrer, ne garder uniquement que les personnes ayant un age > 50
		// ??? filterByAge = ???;
		GenericPredicate<Person> filterByAge = p -> p.getAge() > 50;

		// TODO créer un variable mapToAccount de type GenericMapper
		// TODO transformer la liste de personnes en liste de comptes. Un compte a par
		// défaut un solde à 1000.
		// ??? mapToAccount = ???;
		GenericMapper<Person, Account> mapToAccount = p -> {
			Account acc = new Account();
			acc.setOwner(p);
			acc.setBalance(1000);
			return acc;
		};

		// TODO créer un variable verifyAccount de type Processor
		// TODO vérifier que chaque compte a un solde à 1000.
		// TODO vérifier que chaque titulaire de compte a un age > 50
		// ??? verifyAccount = ???;
		Processor<Account> verifyAccount = acc -> {
			assert acc.getBalance().equals(1000);
			assert acc.getOwner().getAge() > 50;
		};
		// TODO Décommenter
		personFuncCollection.filter(filterByAge).map(mapToAccount).forEach(verifyAccount);

		// TODO A supprimer
		// assert false;
	}
	// end::test_filter_map_forEach_with_vars[]

}
