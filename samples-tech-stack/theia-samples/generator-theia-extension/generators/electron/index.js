"use strict";
/********************************************************************************
 * Copyright (C) 2017 TypeFox and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
Object.defineProperty(exports, "__esModule", { value: true });
const Base = require("yeoman-generator");
module.exports = class TheiaElectron extends Base {
    path() {
        this.sourceRoot(__dirname + '/../../templates');
    }
    writing() {
        this.fs.copyTpl(this.templatePath('app-electron-package.json'), this.destinationPath('electron-app/package.json'), {
            appMode: 'electron',
            params: this.options.params
        });
    }
};
//# sourceMappingURL=index.js.map