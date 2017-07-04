/*
 * Sonar ESQL Plugin
 * Copyright (C) 2013-2017 Thomas Pohl and EXXETA AG
 * http://www.exxeta.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.exxeta.iss.sonar.esql.api.tree.symbol;

import static com.exxeta.iss.sonar.esql.compat.CompatibilityHelper.wrap;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.sonar.api.config.MapSettings;
import org.sonar.api.config.Settings;

import com.exxeta.iss.sonar.esql.api.symbols.Symbol;
import com.exxeta.iss.sonar.esql.api.symbols.Symbol.Kind;
import com.exxeta.iss.sonar.esql.api.tree.ProgramTree;
import com.exxeta.iss.sonar.esql.api.tree.Tree;
import com.exxeta.iss.sonar.esql.compat.CompatibleInputFile;
import com.exxeta.iss.sonar.esql.tree.symbols.SymbolModelImpl;
import com.exxeta.iss.sonar.esql.utils.EsqlTreeModelTest;
import com.exxeta.iss.sonar.esql.utils.TestInputFile;

public class SymbolModelImplTest extends EsqlTreeModelTest<ProgramTree> {

  private static final CompatibleInputFile INPUT_FILE = wrap(new TestInputFile("src/test/resources/ast/resolve/symbolModel.esql"));
  private SymbolModelImpl SYMBOL_MODEL = symbolModel(INPUT_FILE);

  @Test
  public void symbols_filtering() {
    assertThat(SYMBOL_MODEL.getSymbols(Symbol.Kind.FUNCTION)).extracting("name").containsOnly("f", "func");
    assertThat(SYMBOL_MODEL.getSymbols(Symbol.Kind.PROCEDURE)).extracting("name").containsOnly("p1", "p2");

    assertThat(SYMBOL_MODEL.getSymbols("a")).hasSize(2);
  }

  @Test
  public void symbols_scope() {
    Symbol f = (Symbol) SYMBOL_MODEL.getSymbols("f").toArray()[0];
    assertThat(f.scope().tree().is(Tree.Kind.PROGRAM)).isTrue();
  }

  @Test
  public void override_symbol_kind() throws Exception {
    Symbol func = (Symbol) SYMBOL_MODEL.getSymbols("func").toArray()[0];
    assertThat(func.is(Kind.FUNCTION)).isTrue();
  }

  private Settings settings(String environmentNames, String globalNames) {
    return new MapSettings();
  }
}
