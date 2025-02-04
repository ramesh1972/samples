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
module.exports = class TheiaBrowser extends Base {
    path() {
        this.sourceRoot(__dirname + '/../../templates');
    }
    writing() {
        const params = this.options.params;
        this.fs.copyTpl(this.templatePath('app-browser-package.json'), this.destinationPath('browser-app/package.json'), {
            appMode: 'browser',
            params
        });
    }
};
//# sourceMappingURL=index.js.map