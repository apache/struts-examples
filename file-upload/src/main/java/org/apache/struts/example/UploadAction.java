/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts.example;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.UploadedFilesAware;
import org.apache.struts2.dispatcher.multipart.UploadedFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <code>Allows upload a file</code>
 */
public class UploadAction extends ActionSupport implements UploadedFilesAware {

    private File[] upload;
    private String[] uploadFileName;
    private String[] uploadContentType;

    public String execute() throws Exception {
        return INPUT;
    }

    public File[] getUpload() {
        return upload;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public String[] getUploadContentType() {
        return uploadContentType;
    }

    @Override
    public void withUploadedFiles(List<UploadedFile> uploadedFiles) {
        upload = uploadedFiles.stream().map(UploadedFile::getContent).toArray(File[]::new);
        uploadFileName = uploadedFiles.stream().map(UploadedFile::getName).toArray(String[]::new);
        uploadContentType = uploadedFiles.stream().map(UploadedFile::getContentType).toArray(String[]::new);
    }
}
