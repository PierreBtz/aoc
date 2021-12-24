package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7Puzzle1 {
    static final String BAG_OF_INTEREST = "shiny gold";

    public static void main(String[] args) throws IOException {
        var bags = buildBags();
        System.out.println(bags.values().stream()
              .peek(b -> b.decomposeInto(BAG_OF_INTEREST, bags))
              .filter(b -> b.getNumberOf(BAG_OF_INTEREST) > 0)
              .count());
    }

    static Map<String, Bag> buildBags() throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 7)) {
            return reader.lines()
                  .map(Bag::new)
                  .collect(Collectors.toMap(Bag::getName, Function.identity()));
        }
    }

    static class Bag {
        private static final Pattern BAG_ENTRY_PATTERN = Pattern.compile("(?<bagName>\\D+) bags contain (?<contains>.*)\\.");
        private static final Pattern BAG_CONTENT_PATTERN = Pattern.compile("(\\d)+ (.*)bag[s]*");
        private static final String EMPTY_BAG = "no other bags";

        private final Map<String, Integer> content;
        private final String name;

        public Bag(String bagEntry) {
            var matcher = BAG_ENTRY_PATTERN.matcher(bagEntry);
            if (matcher.matches()) {
                name = matcher.group("bagName");
                var c = matcher.group("contains");
                if (EMPTY_BAG.equals(c)) {
                    content = new HashMap<>();
                } else {
                    content = Stream.of(c.split(","))
                          .map(String::trim) // just to be sure as we are basing everything on the bag name
                          .map(BAG_CONTENT_PATTERN::matcher)
                          .peek(Matcher::matches)
                          .collect(Collectors.toMap(m -> m.group(2).trim(), m -> Integer.valueOf(m.group(1))));
                }
            } else {
                throw new UnsupportedOperationException("Something went wrong with entry " + bagEntry);
            }
        }

        Bag(String name, Map<String, Integer> content) {
            this.name = name;
            this.content = new HashMap<>(content);
        }

        public String getName() {
            return name;
        }

        public void decomposeInto(String bagName, Map<String, Bag> bags) {
            System.err.println(this);
            if (!content.isEmpty()) {
                var numberOfBagName = getNumberOf(bagName);
                for (var it = content.keySet().iterator(); it.hasNext(); ) {
                    var key = it.next();
                    if (!key.equals(bagName)) {
                        var bagToDecompose = bags.get(key);
                        bagToDecompose.decomposeInto(bagName, bags);
                        // after this call, we know that bagToDecompose is only composed of empty bags or bagName
                        numberOfBagName += bagToDecompose.getNumberOf(bagName) * getNumberOf(bagToDecompose.name);
                        it.remove();
                    }
                    System.err.println(this);
                }
                content.put(bagName, numberOfBagName);
            }
        }

        public int countNumberofPrimeBags(Map<String, Bag> bags) {
            // this is not the most elegant but with this algorithm we need to remove one bag (which is this itself)
            return internalCountNumberOfPrimeBags(bags) - 1;
        }

        private int internalCountNumberOfPrimeBags(Map<String, Bag> bags) {
            System.err.println(this);
            var result = 1;
            if (!content.isEmpty()) {
                for (var it = content.keySet().iterator(); it.hasNext(); ) {
                    var key = it.next();
                    var bagToDecompose = bags.get(key);
                    result += bagToDecompose.internalCountNumberOfPrimeBags(bags) * getNumberOf(bagToDecompose.name);
                }
            }
            return result;
        }

        public int getNumberOf(String bagName) {
            return content.getOrDefault(bagName, 0);
        }

        @Override
        public String toString() {
            return "Bag{" +
                  "content=" + content +
                  ", name='" + name + '\'' +
                  '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bag bag = (Bag) o;
            return Objects.equals(content, bag.content) && Objects.equals(name, bag.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(content, name);
        }
    }
}
